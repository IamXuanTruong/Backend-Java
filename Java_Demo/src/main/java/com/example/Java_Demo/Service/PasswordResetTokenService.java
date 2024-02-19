package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.PasswordResetToken;
import com.example.Java_Demo.Models.Users;
import com.example.Java_Demo.Repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    public void createPasswordResetTokenForUser(Users user, String passwordToken) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUser(user);
        if(passwordResetToken != null){
            passwordResetTokenRepository.deleteById(passwordResetToken.getToken_id());

        }
        PasswordResetToken passwordRestToken = new PasswordResetToken(passwordToken, user);
        passwordResetTokenRepository.save(passwordRestToken);
    }

    public String validatePasswordResetToken(String passwordResetToken) {
        PasswordResetToken passwordToken = passwordResetTokenRepository.findByToken(passwordResetToken);
        if(passwordToken == null){
            return "Invalid verification token";
        }
        Users user = passwordToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Link already expired, resend link";
        }
        return "valid";
    }
    public Optional<Users> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(passwordResetToken).getUser());
    }

}