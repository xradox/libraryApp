package ru.rado.libraryApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rado.libraryApp.dao.BookDAO;
import ru.rado.libraryApp.dao.PersonDAO;
import ru.rado.libraryApp.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public PersonController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "person/index";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") Person person){
        return "person/new";
    }
    @GetMapping("/{id}")
    public String userPage(@PathVariable int id, Model model){
        model.addAttribute("person", personDAO.showUser(id));
        model.addAttribute("books", bookDAO.getBooksForPerson(id));
        return "person/user_page";
    }
    @PostMapping()
    public String create(@Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person/new";
        }
        personDAO.add(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable int id, Model model){
        model.addAttribute("person", personDAO.showUser(id));
        return "person/edit";
    }
    @PatchMapping("/{id}")
    public String updateUser(@PathVariable int id, @Valid Person updatedPerson,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person/edit";
        }
        personDAO.updateUser(id, updatedPerson);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        personDAO.deleteUser(id);
        return "redirect:/people";
    }
}
