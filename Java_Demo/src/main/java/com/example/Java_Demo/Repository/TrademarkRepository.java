package com.example.Java_Demo.Repository;

import com.example.Java_Demo.Models.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrademarkRepository extends JpaRepository<Trademark,Long> {
}
