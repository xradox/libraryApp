package ru.rado.libraryApp.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {

    private int id;
    @NotEmpty(message = "Название книги не должно быть пустым")
    private String name;
    @NotEmpty(message = "Имя автора книги не должно быть пустым")
    private String author;
    @Min(value = 0, message = "Год должен быть боьше 0, и не больше текущего года")
    @Max(value = 2022, message = "Год должен быть больше 0, и не больше текущего года")
    private int year;

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
