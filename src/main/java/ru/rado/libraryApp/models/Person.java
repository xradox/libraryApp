package ru.rado.libraryApp.models;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[А-ЯЁ][а-яё]{2,}\\s[А-ЯЁ][а-яё]{2,}\\s[А-ЯЁ][а-яё]{2,}",
            message = "ФИО - напр. Петров Петр Петрович")
    private String name;

    @Column(name = "year")
    @Min(value = 1900, message = "Год должен быть от 1900 до 2022")
    @Max(value = 2022, message = "Год должен быть от 1900 до 2022")
    private int year;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
