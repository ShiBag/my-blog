package com.shivam.blog.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shivam.blog.model.Person;
import com.shivam.blog.service.PersonService;

@Controller
public class AccountController {
	
	@Autowired
	PersonService personService;

	@GetMapping("/account")
    public ModelAndView account(Principal principal) {
    	ModelAndView mv = new ModelAndView();
    	String username = principal.getName();
    	Person user = personService.findByUsername(username);
    	mv.addObject("user",user);
    	mv.setViewName("account");
    	return mv;
    }
	
	@PostMapping("/account/update")
    public String account(@ModelAttribute("user") Person person, Principal principal) {
    	ModelAndView mv = new ModelAndView();
    	String username = principal.getName();
    	Person existingUser = personService.findByUsername(username);
    	existingUser.setName(person.getName());
    	existingUser.setUsername(person.getUsername());
    	existingUser.setPassword(person.getPassword());
    	personService.updatePerson(existingUser.getPersonId(), existingUser);
    	return "redirect:/account?success";
    }
	
}
