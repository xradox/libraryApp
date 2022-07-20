package ru.rado.libraryApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rado.libraryApp.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
