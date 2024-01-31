package com.example.Java_Demo.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Setter
@Getter

public class Trademark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trademark_id;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String trademard_name;

}