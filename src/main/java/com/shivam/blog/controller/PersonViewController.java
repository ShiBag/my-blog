package com.shivam.blog.controller;

import com.shivam.blog.model.Person;
import com.shivam.blog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonViewController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String viewPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons"; // Renders persons.html
    }
    
    @GetMapping("/{id}")
    public String viewPersonDetails(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person-details";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/create")
    public String createPerson(Person person) {
        personService.createPerson(person);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person-form";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable Long id, Person person) {
        personService.updatePerson(id, person);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "redirect:/";
    }
}
