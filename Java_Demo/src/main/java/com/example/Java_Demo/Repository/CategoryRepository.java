package com.example.Java_Demo.Repository;

import com.example.Java_Demo.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
