package br.com.kaudotdev.biblioteca.config;

import br.com.kaudotdev.biblioteca.entities.Role;
import br.com.kaudotdev.biblioteca.entities.User;
import br.com.kaudotdev.biblioteca.repository.RoleRepository;
import br.com.kaudotdev.biblioteca.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        if (roleAdmin == null) {
            throw new IllegalStateException("Role ADMIN não existe no banco de dados. Crie o papel antes de iniciar a aplicação.");
        }
        var userAdmin = userRepository.findByName("admin");

        userAdmin.ifPresentOrElse(
                user -> {System.out.println("admin já existe");
                },
                () -> {
                    var user = new User();
                    user.setName("admin");
                    user.setPassword(passwordEncoder.encode("admin"));
                    user.setRole(roleAdmin);
                    userRepository.save(user);
                }
        );


    }

}

