package com.example.Java_Demo.Service;

import com.example.Java_Demo.Models.Product;
import com.example.Java_Demo.Repository.CategoryRepository;
import com.example.Java_Demo.Repository.ProductRepository;
import com.example.Java_Demo.Repository.TrademarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TrademarkRepository trademarkRepository;
    @Autowired
    public ProductService (ProductRepository productRepository, CategoryRepository categoryRepository, TrademarkRepository trademarkRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.trademarkRepository = trademarkRepository;
    }
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product createProduct(Product product) {
        Product product1 = new Product();
        product1.setProduct_name(product.getProduct_name());
        product1.setProduct_images(product.getProduct_images());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        product1.setQuantity(product.getQuantity());
        if (product.getTrademark() != null) {
            Long trademarkId = product.getTrademark().getTrademark_id();
            product1.setTrademark(trademarkRepository.findById(trademarkId).orElseThrow(() -> new RuntimeException("Trademark not found")));
        }
        if (product.getCategory() != null) {
            Long categoryId = product.getCategory().getCategory_id();
            product1.setCategory(categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found")));
        }

        return productRepository.save(product1);
    }

}
