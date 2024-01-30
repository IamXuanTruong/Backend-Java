package com.example.Java_Demo.Exception;

public class NonActiveAccountExcaption extends RuntimeException {
    public NonActiveAccountExcaption(String message) {
        super(message);
    }
}
