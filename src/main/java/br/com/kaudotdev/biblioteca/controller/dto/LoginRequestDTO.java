package br.com.kaudotdev.biblioteca.controller.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String name;
    private String password;

    public LoginRequestDTO() {}

    public LoginRequestDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

