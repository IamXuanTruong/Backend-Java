package com.example.Java_Demo.Request;

public record LoginRequest(
        String email,
        String password) {
}