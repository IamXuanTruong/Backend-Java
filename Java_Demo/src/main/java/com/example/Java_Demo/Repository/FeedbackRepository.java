package com.example.Java_Demo.Repository;

import com.example.Java_Demo.Models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
