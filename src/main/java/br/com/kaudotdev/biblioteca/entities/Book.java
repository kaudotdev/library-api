package br.com.kaudotdev.biblioteca.entities;

import br.com.kaudotdev.biblioteca.entities.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Integer publishedDate;
    private Boolean isAvailable;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime registeredAt;

    public Book(Long id) {
        this.id = id;
    }
    public Book() {}
}
