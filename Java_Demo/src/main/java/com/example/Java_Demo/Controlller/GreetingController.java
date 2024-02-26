package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@AllArgsConstructor
public class GreetingController {
    public final UserService userService;
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("activeKey") String activeKey, Model model){
        if(userService.validActiveKey(activeKey)){
            userService.updateIsEnabled(activeKey);
            model.addAttribute("");
            return  "greeting";
        }
        return "The Verify time has expired, please click resend email\n";

    }
}
