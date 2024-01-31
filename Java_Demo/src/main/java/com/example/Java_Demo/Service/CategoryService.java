package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.Category;
import com.example.Java_Demo.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> GetallCategory(){
        return categoryRepository.findAll();
    }
    public Category createCategory(Category category){
        Category category1 = new Category();
        category1.setCategory_name(category.getCategory_name());
        return categoryRepository.save(category1);
    }
    public Optional<Category> getCategoryid(Long id){
        return categoryRepository.findById(id);
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
