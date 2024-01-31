package com.example.Java_Demo.Repository;

import com.example.Java_Demo.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
