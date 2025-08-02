package com.shivam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shivam.blog.model.Person;
import com.shivam.blog.repository.PersonRepository;

@Controller
public class AuthController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("person", new Person());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Person person) {
        person.setPassword(person.getPassword());
        personRepository.save(person);
        return "redirect:/login?registered";
    }
}
