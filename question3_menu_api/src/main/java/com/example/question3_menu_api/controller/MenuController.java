package com.example.question3_menu_api.controller;

import com.example.question3_menu_api.model.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menu = new ArrayList<>();

    public MenuController() {
        menu.add(new MenuItem(1L, "Burger", "Beef burger", 1000.00, "Main Course", true));
        menu.add(new MenuItem(2L, "Pizza", "Cheese pizza", 4200.0, "Main Course", true));
        menu.add(new MenuItem(3L, "Ice Cream", "Vanilla dessert", 800.0, "Dessert", true));
        menu.add(new MenuItem(4L, "Coke", "Soft drink", 600.0, "Beverage", false));
        menu.add(new MenuItem(5L, "Fries", "Potato fries", 1500.0, "Appetizer", true));
        menu.add(new MenuItem(6L, "Salad", "Green salad", 3500.0, "Appetizer", true));
        menu.add(new MenuItem(7L, "Coffee", "Hot coffee", 2500.0, "Beverage", true));
        menu.add(new MenuItem(8L, "Cake", "Chocolate cake", 4500.0, "Dessert", true));
    }

  
    @GetMapping
    public List<MenuItem> getAll() {
        return menu;
    }

    
    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) {
        return menu.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    
    @GetMapping("/category/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category) {
        return menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .toList();
    }

  
    @GetMapping("/available")
    public List<MenuItem> getAvailable(@RequestParam boolean available) {
        return menu.stream()
                .filter(item -> item.isAvailable() == available)
                .toList();
    }

    
    @GetMapping("/search")
    public List<MenuItem> searchByName(@RequestParam String name) {
        return menu.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    
    @PostMapping
    public MenuItem addItem(@RequestBody MenuItem item) {
        menu.add(item);
        return item;
    }

    
    @PutMapping("/{id}/availability")
    public MenuItem toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return item;
            }
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        menu.removeIf(item -> item.getId().equals(id));
    }
}
