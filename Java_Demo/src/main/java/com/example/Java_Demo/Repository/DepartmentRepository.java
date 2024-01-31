package com.example.Java_Demo.Repository;

import com.example.Java_Demo.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
