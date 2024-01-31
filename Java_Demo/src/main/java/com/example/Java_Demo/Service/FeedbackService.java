package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.Feedback;
import com.example.Java_Demo.Models.Users;
import com.example.Java_Demo.Repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    public List<Feedback>getallFeedback(){
        return feedbackRepository.findAll();
    }
    public Feedback createFeedback(Feedback feedback){
        Users users = userService.getUserLogining();
        Feedback feedback1 = new Feedback();
        feedback1.setFeedback(feedback.getFeedback());
        feedback1.setUsers(users);
        return feedbackRepository.save(feedback1);
    }
    public void deleteFeedback(Long id){
        feedbackRepository.deleteById(id);
    }
}
