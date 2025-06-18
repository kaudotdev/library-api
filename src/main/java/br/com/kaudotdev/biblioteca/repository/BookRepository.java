package br.com.kaudotdev.biblioteca.repository;

import br.com.kaudotdev.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
