package ru.rado.libraryApp.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.rado.libraryApp.models.Person;

import java.util.List;
@Deprecated
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, year) values(?, ?)", person.getName(), person.getYear());
    }
    public Person showUser(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findFirst().orElse(null);
    }
    public void updateUser(int id, Person updated) {
        jdbcTemplate.update("UPDATE Person SET name = ?, year = ? WHERE id = ?",
                updated.getName(), updated.getYear(), id);
    }
    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }


}
