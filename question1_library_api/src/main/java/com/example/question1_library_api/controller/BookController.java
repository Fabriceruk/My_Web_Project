package com.example.question1_library_api.controller;

import com.example.question1_library_api.model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "Programming with C", "Muhire John", "978-0132350884", 2010));
        books.add(new Book(2L, "Java Programming", "Mugabo Erick", "978-1617294945", 2012));
        books.add(new Book(3L, "Web Technology", "Karekezi Joshua ", "978-0134685991", 2014));
    }

    
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        books.add(book);
        return ResponseEntity.status(201).body(book);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        return ResponseEntity.noContent().build();
    }
}
