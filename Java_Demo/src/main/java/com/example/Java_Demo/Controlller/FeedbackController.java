package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Models.Feedback;
import com.example.Java_Demo.Models.Users;
import com.example.Java_Demo.Repository.FeedbackRepository;
import com.example.Java_Demo.Service.FeedbackService;
import com.example.Java_Demo.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth/api/feedback")
public class FeedbackController {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackService feedbackService;
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<Feedback>>getAllfeedback(){
        List<Feedback> feedbacks = feedbackService.getallFeedback();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createFeedback(@RequestBody Feedback feedback) {
        List<Users> users = userService.getprofile();
        Feedback createFeedback = feedbackService.createFeedback(feedback);
        String message = "Feedback post successfully " + createFeedback.getFeedback_id();
        return ResponseEntity.ok().body(message);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id){
        if(feedbackRepository.existsById(id)){
            feedbackRepository.deleteById(id);
            return  ResponseEntity.ok("ok");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("k the xoa");
        }
    }
}
