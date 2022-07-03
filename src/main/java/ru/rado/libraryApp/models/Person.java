package ru.rado.libraryApp.models;


import javax.validation.constraints.*;

public class Person {

    private int id;
    @Pattern(regexp = "[А-ЯЁ][а-яё]{2,}\\s[А-ЯЁ][а-яё]{2,}\\s[А-ЯЁ][а-яё]{2,}",
            message = "ФИО - напр. Петров Петр Петрович")
    private String name;
    @Min(value = 1900, message = "Год должен быть от 1900 до 2022")
    @Max(value = 2022, message = "Год должен быть от 1900 до 2022")
    private int year;

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
}
