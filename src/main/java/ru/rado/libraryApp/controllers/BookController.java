package ru.rado.libraryApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rado.libraryApp.dao.BookDAO;
import ru.rado.libraryApp.dao.PersonDAO;
import ru.rado.libraryApp.models.Book;
import ru.rado.libraryApp.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookDAO bookDAO;

    private final PersonDAO personDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model, @ModelAttribute("person1") Person person){
        model.addAttribute("book", bookDAO.showBook(id));
        model.addAttribute("peopleList", personDAO.index());
        model.addAttribute("personCheck", bookDAO.checkBookByPerson(id).isPresent());
        model.addAttribute("person", bookDAO.checkBookByPerson(id).orElse(null));
        return "book/show";
    }
    @PostMapping
    public String addBook(@Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/new";
        }
        bookDAO.addBook(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable int id, Model model){
        model.addAttribute("book", bookDAO.showBook(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @Valid Book updated, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/edit";
        }
        bookDAO.update(id, updated);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/addPerson")
    public String addPerson(@PathVariable int id, @ModelAttribute("person1")Person person){
        bookDAO.addPersonToBook(id, person);
        return "redirect:/book/{id}";
    }
    @PatchMapping("/{id}/clear")
    public String clearOwner(@PathVariable int id){
        bookDAO.clearOwner(id);
        return "redirect:/book/{id}";
    }
}
