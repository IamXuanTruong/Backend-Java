package com.example.Java_Demo.Request;

import com.example.Java_Demo.Common.Role;

public record RegistrationRequest(
        String username,
        String email,
        String images,
        String password,
        Role role,
        String address,
        String phoneNumber
) {
}