package br.com.kaudotdev.biblioteca.services;


import br.com.kaudotdev.biblioteca.entities.Book;
import br.com.kaudotdev.biblioteca.entities.Loan;
import br.com.kaudotdev.biblioteca.entities.User;
import br.com.kaudotdev.biblioteca.repository.BookRepository;
import br.com.kaudotdev.biblioteca.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public Loan registerLoan(UUID userId, Long bookId) {
        boolean isAvailable = loanRepository.findByBookIdAndStatus(bookId, Loan.LoanStatus.ATIVO).isEmpty();
        if (!isAvailable) throw new RuntimeException("Book is not available for loan.");

        Loan loan = new Loan();
        loan.setUser(new User(userId));
        loan.setBook(new Book(bookId));
        loan.setBorrowedAt(LocalDateTime.now());
        loan.setDevolutionPromptAt(LocalDateTime.now().plusDays(14));
        loan.setStatus(Loan.LoanStatus.ATIVO);
        return loanRepository.save(loan);
    }

    public List<Loan> getLoansByUser(UUID userId) {
        return loanRepository.findByUserId(userId);
    }

    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found."));

        if (loan.getStatus() != Loan.LoanStatus.ATIVO) {
            throw new RuntimeException("Loan is not active.");
        }

        loan.setReturnedAt(LocalDateTime.now());
        loan.setStatus(Loan.LoanStatus.DEVOLVIDO);
        return loanRepository.save(loan);
    }

    public void updateOverdueLoans() {
        List<Loan> overdueLoans = loanRepository.findByBookIdAndStatus(null, Loan.LoanStatus.ATIVO);
        LocalDateTime now = LocalDateTime.now();

        for (Loan loan : overdueLoans) {
            if (loan.getDevolutionPromptAt().isBefore(now)) {
                loan.setStatus(Loan.LoanStatus.ATRASADO);
                loanRepository.save(loan);
            }
        }
    }
}
