//package com.example.Java_Demo.Controlller;
//
//import com.example.Java_Demo.Models.Category;
//import com.example.Java_Demo.Repository.CategoryRepository;
//import com.example.Java_Demo.Service.CategoryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/category")
//public class CategoryController {
//    private final CategoryRepository categoryReponsitory;
//    private final CategoryService categoryService;
//    @GetMapping
//    public List<Category> getCategories(){
//        return categoryReponsitory.findAll();
//    }
////    @PostMapping("/create")
////    public ResponseEntity<String> createproduct(@RequestBody Category category){
////        categoryService.CreateCategory(category);
////        return ResponseEntity.ok().body("okkk");
////    }
////    @DeleteMapping("/delete/{id}")
////    public String deletecategory(@PathVariable Long id){
////        if (categoryReponsitory.existsById (id)){
////            categoryReponsitory.deleteById(id);
////            return "delete ok";
////        }else {
////            return "delete err";
////        }
////    }
////    @PutMapping("/update/{id}")
////    public ResponseEntity<Category> updateProduct(@PathVariable Long id, @RequestBody Category updateCategory) {
////        Category exCategory = categoryReponsitory.findById(id)
////                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
////
////        exCategory.setCategory_name(updateCategory.getCategory_name());
////        return ResponseEntity.ok(categoryReponsitory.save(exCategory));
////    }
//
//
//}