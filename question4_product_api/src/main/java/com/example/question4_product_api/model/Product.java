package com.example.question4_product_api.model;

public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private int stockQuantity;
    private boolean available;

    public Product() {

    }

    public Product(Long id, String name, String description, Double price, String category, int stockQuantity, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.available = available;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id;
    }

    public String getName() {
    return name; 
    }

    public void setName(String name) { 
    this.name = name; 
}

    public String getDescription() {
    return description; 
    }
    public void setDescription(String description) { 
    this.description = description; 
    }

    public Double getPrice() {
    return price; 
    }

    public void setPrice(Double price) {
    this.price = price; 
    }

    public String getCategory() { 
    return category; 
    }
    public void setCategory(String category) { 
    this.category = category; 
    }

    public int getStockQuantity() {
    return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity) { 
    this.stockQuantity = stockQuantity; 
    }

    public boolean isAvailable() { 
    return available; 
    }
    public void setAvailable(boolean available) {
    this.available = available; 
    }
}
