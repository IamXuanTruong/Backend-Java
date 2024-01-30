package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Respose.UserReponse;
import com.example.Java_Demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping()
    public ResponseEntity<UserReponse> GetProfileUser(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.GetProfileUser(email));
    }
}