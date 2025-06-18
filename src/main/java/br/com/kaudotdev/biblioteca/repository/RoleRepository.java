package br.com.kaudotdev.biblioteca.repository;

import br.com.kaudotdev.biblioteca.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
