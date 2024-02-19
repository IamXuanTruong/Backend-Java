package com.example.Java_Demo.Service;

import com.example.Java_Demo.Exception.UserAlreadyExistsException;
import com.example.Java_Demo.Models.Users;
import com.example.Java_Demo.Repository.UsersRepository;
import com.example.Java_Demo.Request.LoginRequest;
import com.example.Java_Demo.Request.RegistrationRequest;
import com.example.Java_Demo.Respose.LoginReponse;
import com.example.Java_Demo.Respose.UserReponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final TemplateEngine templateEngine;
    private final AuthenticationManager authenticationManager;
    private final PasswordResetTokenService passwordResetTokenService;

    private final JwtService jwtService;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void registerUser(RegistrationRequest request) throws MessagingException, IOException {
        Optional<Users> user = this.findByEmail(request.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with email " + request.email() + " already exists");
        }
        var newUser = new Users();
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        newUser.setAddress(request.address());
        newUser.setPhoneNumber(request.phoneNumber());
        newUser.setActiveKey(this.generateActiveKey());
        this.sendEmail(newUser,"");
        usersRepository.save(newUser);
    }
    public void updateIsEnabled(String activeKey){
        Optional<Users> newUser = usersRepository.findByActiveKey(activeKey);
        if(newUser.isPresent()){
            usersRepository.updateIsEnable(activeKey);
        }

    }

    public void ResendEmailVerify(String email){
        Users newUser =  this.findByEmail(email).orElseThrow();
        newUser.setActiveKey(generateActiveKey());
        usersRepository.save(newUser);
        this.sendEmail(newUser,"");
    }

    public LoginReponse Login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        ));
        var user = usersRepository.findByEmail(loginRequest.email()).orElseThrow();
        return new LoginReponse(jwtService.generateToken(user));
    }

    public UserReponse GetProfileUser(String email){
        Users user = this.findByEmail(email).orElseThrow();
        return new UserReponse(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),
                user.getRole(),user.getAddress(),user.getPhoneNumber());
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
    public Optional<Users> findUserByResetPasswordToken(String token) {
        return passwordResetTokenService.findUserByPasswordToken(token);
    }



    @Override
    public String generateActiveKey() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String ActiveTime = sdf.format(new Date(System.currentTimeMillis() + 1000 * 60 * 5));
        return ActiveTime + UUID.randomUUID();
    }

    @Override
    public boolean validActiveKey(String activeKey) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date CurrentTime = new Date();
            String ActiveTime = activeKey.substring(0,14);

            return CurrentTime.before(sdf.parse(ActiveTime));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public void changePassword(Users theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(theUser);
    }
    @Override
    public boolean oldPasswordIsValid(Users users, String oldPassword){
        return passwordEncoder.matches(oldPassword, users.getPassword());
    }


    @Override
    public void createPasswordResetTokenForUser(Users users, String resetPasswordToken) {
        passwordResetTokenService.createPasswordResetTokenForUser(users,resetPasswordToken);
    }

    public String validatePasswordResetToken(String token){
        return passwordResetTokenService.validatePasswordResetToken(token);
    }

    public Users getUserLogining(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (Users) authentication.getPrincipal();

        }
        return null;
    }
    public void sendEmail(Users newUser, String token){
        try {
            String emailFrom = "truongept2003@gmail.com";
            String subject = "Chào mừng bạn đến với thành phố của những giấc mơ";

            // Tạo context Thymeleaf
            Context context = new Context();
            Object[] Variable = {
                    newUser,
                    token
            };
            context.setVariable("user", newUser);

            // Tạo HTML từ template và context
            String htmlContent = templateEngine.process("SendVerifyEmail", context);
            if(!token.isBlank()){
                context.setVariable("Variable",Variable);
                subject = "Request reset BeeClothes password!";
                htmlContent = templateEngine.process("RequestResetPassword", context);
            }
            // Tạo MimeMessage và thiết lập nội dung HTML
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(emailFrom);
            helper.setTo(newUser.getEmail());
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            // Gửi email
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Xử lý ngoại lệ
            e.printStackTrace(); // Hoặc thực hiện xử lý khác
        }
    }

    public List<Users> getprofile() {
        return usersRepository.findAll();
    }
}