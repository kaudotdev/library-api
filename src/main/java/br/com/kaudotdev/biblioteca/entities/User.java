package br.com.kaudotdev.biblioteca.entities;

import br.com.kaudotdev.biblioteca.controller.dto.LoginRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(UUID id) {
        this.id = id;
    }
    public User() {}

    private Boolean validatePassword(){
        return this.password != null && !this.password.isEmpty() && this.password.length() >= 8
                && this.password.matches(".*[a-zA-Z].*")
                && this.password.matches(".*\\d.*")
                && this.password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }


}
