package br.com.kaudotdev.biblioteca.controller;

import br.com.kaudotdev.biblioteca.controller.dto.LoginRequestDTO;
import br.com.kaudotdev.biblioteca.controller.dto.LoginResponse;
import br.com.kaudotdev.biblioteca.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDTO loginRequest){
        var user = userRepository.findByName(loginRequest.getName());
        if(user.isEmpty() || !bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("User or password is invalid");
        }
        var now = Instant.now();
        var expiresIn = 300L;
        var scope = user.get().getRole() != null ? user.get().getRole().getName().toUpperCase() : "";
        var claims = JwtClaimsSet.builder()
                .issuer("library")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scope)
                .claim("authorities", scope)
                .build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(
                new LoginResponse(jwtValue, expiresIn)
        );
    }
}
