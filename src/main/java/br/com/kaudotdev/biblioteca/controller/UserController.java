package br.com.kaudotdev.biblioteca.controller;

import br.com.kaudotdev.biblioteca.controller.dto.UserDTO;
import br.com.kaudotdev.biblioteca.controller.dto.CreateUserDto;
import br.com.kaudotdev.biblioteca.entities.User;
import br.com.kaudotdev.biblioteca.repository.UserRepository;
import br.com.kaudotdev.biblioteca.repository.RoleRepository;
import br.com.kaudotdev.biblioteca.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return userRepository.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.name());
        user.setEmail(createUserDto.email());
        user.setPassword(passwordEncoder.encode(createUserDto.password()));
        Role basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        if (basicRole == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setRole(basicRole);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new UserDTO(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            basicRole.getName()
        ));
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole() != null ? user.getRole().getName() : null
        );
    }
}
