package ru.rado.libraryApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rado.libraryApp.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
