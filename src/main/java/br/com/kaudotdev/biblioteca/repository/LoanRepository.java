package br.com.kaudotdev.biblioteca.repository;

import br.com.kaudotdev.biblioteca.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(UUID user_id);
    List<Loan> findByBookIdAndStatus(Long book_id, Loan.LoanStatus status);


}
