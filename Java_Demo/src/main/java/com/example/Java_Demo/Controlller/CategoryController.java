package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Models.Category;
import com.example.Java_Demo.Repository.CategoryRepository;
import com.example.Java_Demo.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>>getAllCategory(){
        List<Category> categories= categoryService.GetallCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return ResponseEntity.ok().body("+1");
    }
    @GetMapping("/detail/{id}")
    public Category getCategoryid(@PathVariable Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("err");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {

        Category exCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        exCategory.setCategory_name(category.getCategory_name());
        Category updatedCategory = categoryRepository.save(exCategory);
        return ResponseEntity.ok(updatedCategory);
    }
}
