package ru.rado.libraryApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rado.libraryApp.models.Person;
import ru.rado.libraryApp.repositories.PersonRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personRepository.findAll());
        return "person/index";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") Person person){
        return "person/new";
    }
    @GetMapping("/{id}")
    public String userPage(@PathVariable int id, Model model){
        Optional<Person> person = personRepository.findById(id);
        if(person.isPresent()){
            model.addAttribute("person", person.get());
            model.addAttribute("books", person.get().getBooks());
        }
        return "person/user_page";
    }
    @PostMapping()
    public String create(@Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person/new";
        }
        personRepository.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable int id, Model model){
        Optional<Person> person = personRepository.findById(id);
        person.ifPresent(value -> model.addAttribute("person", value));
        return "person/edit";
    }
    @PatchMapping("/{id}")
    public String updateUser(@Valid Person updatedPerson,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "person/edit";
        }
        personRepository.save(updatedPerson);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        personRepository.deleteById(id);
        return "redirect:/people";
    }
}
