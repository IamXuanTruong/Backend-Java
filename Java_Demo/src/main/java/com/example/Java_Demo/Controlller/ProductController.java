package com.example.Java_Demo.Controlller;

import com.example.Java_Demo.Models.Product;
import com.example.Java_Demo.Repository.CategoryRepository;
import com.example.Java_Demo.Repository.ProductRepository;
import com.example.Java_Demo.Repository.TrademarkRepository;
import com.example.Java_Demo.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final TrademarkRepository trademarkRepository;
    private final CategoryRepository categoryRepository;
    @GetMapping
    public ResponseEntity<List<Product>>getallProduct(){
        List<Product> products = productService.getAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return ResponseEntity.ok().body("+1");
    }
    @GetMapping("/detail/{id}")
    public Product getProductid(@PathVariable Long id){
        return productRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductid(@PathVariable Long id){
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
            return ResponseEntity.ok("ok");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("err");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Product product1 = productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found"));
        product1.setProduct_name(product.getProduct_name());
        product1.setTrademark(trademarkRepository.findById(product.getTrademark().getTrademark_id()).orElseThrow());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        product1.setDescription(product.getDescription());
        product1.setProduct_images(product.getProduct_images());
        product1.setCategory(categoryRepository.findById(product.getCategory().getCategory_id()).orElseThrow());
        Product updateProduct = productRepository.save(product1);
        return ResponseEntity.ok(updateProduct);
    }

}
