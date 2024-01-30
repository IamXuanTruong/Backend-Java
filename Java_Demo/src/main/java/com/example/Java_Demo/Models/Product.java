package com.example.Java_Demo.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String product_name;
    private String product_images;
    private String price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String quantity;
    @ManyToOne
    @JoinColumn(name = "trademark_id")
    private Trademark trademark;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;
}