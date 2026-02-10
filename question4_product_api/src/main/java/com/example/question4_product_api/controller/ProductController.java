package com.example.question4_product_api.controller;

import com.example.question4_product_api.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        products.add(new Product(1L, "Laptop", "Dell i7", 900.0, "Electronics", 10, true));
        products.add(new Product(2L, "Phone", "Samsung Galaxy", 600.0, "Electronics", 15, true));
        products.add(new Product(3L, "Shoes", "Nike", 120.0, "Fashion", 20, true));
        products.add(new Product(4L, "Watch", "Smart Watch", 200.0, "Electronics", 5, false));
        products.add(new Product(5L, "Bag", "Leather Bag", 80.0, "Fashion", 8, true));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/search")
    public List<Product> searchByName(@RequestParam String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @GetMapping("/price")
    public List<Product> getByPriceRange(@RequestParam double min, @RequestParam double max) {
        return products.stream()
    .filter(p -> p.getPrice() >= min && p.getPrice() <= max).toList();
    }

    
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {

        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setName(updatedProduct.getName());
                p.setDescription(updatedProduct.getDescription());
                p.setPrice(updatedProduct.getPrice());
                p.setCategory(updatedProduct.getCategory());
                p.setStockQuantity(updatedProduct.getStockQuantity());
                p.setAvailable(updatedProduct.isAvailable());
                return p;
            }
        }
        return null;
    }

    
    @PatchMapping("/{id}/stock")
    public Product updateStock(@PathVariable Long id, @RequestParam int quantity) {

        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setStockQuantity(quantity);
                return p;
            }
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
