package ru.rado.libraryApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rado.libraryApp.models.Book;
import ru.rado.libraryApp.models.Person;
import ru.rado.libraryApp.repositories.BookRepository;
import ru.rado.libraryApp.repositories.PersonRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    private final PersonRepository personRepository;

    public BookController(BookRepository bookRepository, PersonRepository personRepository) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model, @ModelAttribute("person") Person person){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            model.addAttribute("book", book.get());
            if(book.get().getOwner()!=null){
                model.addAttribute("owner", book.get().getOwner());
            } else {
                model.addAttribute("peopleList", personRepository.findAll());
            }
        }
        return "book/show";
    }
    @PostMapping
    public String addBook(@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        bookRepository.save(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable int id, Model model){
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(value -> model.addAttribute("book", value));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@Valid Book updated, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/edit";
        }
        bookRepository.save(updated);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/addPerson")
    public String addPerson(@PathVariable int id, @ModelAttribute("person1")Person person){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            book.get().setOwner(person);
            bookRepository.save(book.get());
        }

        return "redirect:/book/{id}";
    }
    @PatchMapping("/{id}/clear")
    public String clearOwner(@PathVariable int id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            book.get().setOwner(null);
            bookRepository.save(book.get());
        }
        return "redirect:/book/{id}";
    }
}
