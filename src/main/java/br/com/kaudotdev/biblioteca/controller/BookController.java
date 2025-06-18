package br.com.kaudotdev.biblioteca.controller;

import br.com.kaudotdev.biblioteca.entities.Book;
import br.com.kaudotdev.biblioteca.entities.enums.Category;
import br.com.kaudotdev.biblioteca.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/books/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable String category) {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            List<Book> books = bookRepository.findAll().stream()
                    .filter(book -> book.getCategory() == categoryEnum)
                    .toList();
            return ResponseEntity.ok(books);
    }
    
    @PostMapping("/api/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        book.setRegisteredAt(java.time.LocalDateTime.now());
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/api/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setCategory(bookDetails.getCategory());
                    book.setPublishedDate(bookDetails.getPublishedDate());
                    book.setRegisteredAt(java.time.LocalDateTime.now());
                    Book updatedBook = bookRepository.save(book);
                    return ResponseEntity.ok(updatedBook);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }




}
