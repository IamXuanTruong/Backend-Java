package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.Users;
import com.example.Java_Demo.Request.LoginRequest;
import com.example.Java_Demo.Request.RegistrationRequest;
import com.example.Java_Demo.Respose.LoginReponse;
import com.example.Java_Demo.Respose.UserReponse;
import jakarta.mail.MessagingException;

import java.util.Optional;
import java.io.IOException;
public interface IUserService {
    void registerUser(RegistrationRequest request) throws MessagingException, IOException;
    void updateIsEnabled(String activeKey);
    LoginReponse Login(LoginRequest loginRequest);
    UserReponse GetProfileUser(String email);

    void sendEmail(Users newUser, String token);

    Optional<Users> findByEmail(String email);

    String generateActiveKey();


    boolean validActiveKey(String activeKey);

    boolean oldPasswordIsValid(Users users, String oldPassword);

    void createPasswordResetTokenForUser(Users users, String resetPasswordToken);
}