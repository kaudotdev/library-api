package br.com.kaudotdev.biblioteca.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoanRequest {
    private UUID userId;
    private Long bookId;

}