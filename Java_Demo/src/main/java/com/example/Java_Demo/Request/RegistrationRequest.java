package com.example.Java_Demo.Request;

import com.example.Java_Demo.Common.Role;

public record RegistrationRequest (
        String firstName,
        String lastName,
        String email,
        String password,
        Role role,
        String address,
        String phoneNumber) {
}