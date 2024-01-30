package com.example.Java_Demo.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long staff_id;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String fullname;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String address;
    private String images;
    private String phone;
    private Date join_date;
    private Boolean sex;
    private Date eya;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}