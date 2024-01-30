package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Exception.UserAlreadyExistsException;
import com.example.Java_Demo.Models.Users;
import com.example.Java_Demo.Request.LoginRequest;
import com.example.Java_Demo.Request.RegistrationRequest;
import com.example.Java_Demo.Respose.LoginReponse;
import com.example.Java_Demo.Service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/register/profile")
    public ResponseEntity<List<Users>> getprofile() {
        List<Users> users = userService.getprofile();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) throws MessagingException, IOException {
        try {
            userService.registerUser(registrationRequest);
            return ResponseEntity.ok("Please check your email to active account");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email " + registrationRequest.email() + " already exists");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginReponse> Login(@RequestBody LoginRequest loginRequest) {
        Users user = userService.findByEmail(loginRequest.email()).orElseThrow();
        if (!user.isEnabled()) {
            return ResponseEntity.badRequest().body(new LoginReponse(""));

        }
        return ResponseEntity.ok(userService.Login(loginRequest));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("activeKey") String activeKey) {
        if (userService.validActiveKey(activeKey)) {
            userService.updateIsEnabled(activeKey);
            return ResponseEntity.ok("Verify successfully");
        }
        return ResponseEntity.badRequest().body("The Verify time has expired, please click resend email\n");

    }

    @PostMapping("/resend-verify")
    public ResponseEntity<String> ResendverifyEmail(@RequestParam("email") String email) {
        userService.ResendEmailVerify(email);
        return ResponseEntity.ok("Please check your email to active account");
    }

}