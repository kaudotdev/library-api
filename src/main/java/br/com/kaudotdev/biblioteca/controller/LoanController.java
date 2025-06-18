package br.com.kaudotdev.biblioteca.controller;

import br.com.kaudotdev.biblioteca.entities.Loan;
import br.com.kaudotdev.biblioteca.services.LoanService;
import br.com.kaudotdev.biblioteca.controller.dto.LoanRequest;
import br.com.kaudotdev.biblioteca.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRepository loanRepository;

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<Loan> registerLoan(@RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.registerLoan(loanRequest.getUserId(), loanRequest.getBookId());
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PostMapping("/return/{loanId}")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long loanId) {
        Loan loan = loanService.returnLoan(loanId);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable UUID userId) {
        List<Loan> loans = loanService.getLoansByUser(userId);
        return ResponseEntity.ok(loans);
    }
}