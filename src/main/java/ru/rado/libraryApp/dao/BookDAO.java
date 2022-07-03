package ru.rado.libraryApp.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rado.libraryApp.models.Book;
import ru.rado.libraryApp.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void addBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) values (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }
    public Book showBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?",
                new BeanPropertyRowMapper<>(Book.class) ,id).stream().findAny().orElse(null);
    }
    public void update(int id, Book updated) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE id = ?",
                updated.getName(), updated.getAuthor(), updated.getYear(), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }
    public List<Book> getBooksForPerson(int id) {
        return jdbcTemplate.query("SELECT book.name, book.author, book.year from book join person" +
                        " on book.person_id = person.id where person_id = ?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }
    public Optional<Person> checkBookByPerson(int id){
        return jdbcTemplate.query("SELECT person.id, person.name, person.year FROM person join book " +
                "on person.id = book.person_id WHERE book.id = ?", new BeanPropertyRowMapper<>(Person.class), id).
                stream().findAny();
    }
    public void addPersonToBook(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE id = ?", person.getId(), id);
    }
    public void clearOwner(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = null WHERE id = ?", id);
    }
}
