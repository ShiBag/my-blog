package com.shivam.blog.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shivam.blog.model.Person;
import com.shivam.blog.model.Post;
import com.shivam.blog.repository.PostRepository;
import com.shivam.blog.service.PersonService;
import com.shivam.blog.service.PostService;

@Controller
public class HomeController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	PersonService personService;
	
	@Autowired
	PostRepository postRepository;

    @GetMapping("/")
    public ModelAndView home(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Principal principal) {
        ModelAndView mv = new ModelAndView();
        String username = principal.getName();
        Person loggedInUser = personService.findByUsername(username);
        Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size, Sort.by("createdAt").descending()));
        
        mv.addObject("postPage", postPage);
        mv.addObject("currentPage", page);
        mv.addObject("totalPages",postPage.getTotalPages());
        
        mv.addObject("user", loggedInUser);
        mv.setViewName("index");
        return mv;// Renders home.html
    }
}
