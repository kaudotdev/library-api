package br.com.kaudotdev.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;
    private LocalDateTime borrowedAt;
    private LocalDateTime devolutionPromptAt;
    private LocalDateTime returnedAt;
    private LoanStatus status;


    public enum LoanStatus {
        ATIVO, DEVOLVIDO, ATRASADO
    }


}
