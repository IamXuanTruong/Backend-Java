package com.example.Java_Demo.Repository;

import com.example.Java_Demo.Models.PasswordResetToken;
import com.example.Java_Demo.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String passwordResetToken);
    PasswordResetToken findByUser(Users user);
}