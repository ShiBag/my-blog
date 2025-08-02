package com.shivam.blog.controller;

import com.shivam.blog.model.Person;
import com.shivam.blog.model.Post;
import com.shivam.blog.model.Tag;
import com.shivam.blog.service.CategoryService;
import com.shivam.blog.service.PersonService;
import com.shivam.blog.service.PostService;
import com.shivam.blog.service.TagService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class PostViewController {

    @Autowired
    private PostService postService;
    @Autowired 
    private CategoryService categoryService;
    @Autowired 
    private PersonService personService;
    @Autowired 
    private TagService tagService;

    @GetMapping("/posts")
    public String viewPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "posts"; // Renders posts.html
    }
    
    @GetMapping("/my-posts")
    public ModelAndView viewMyPosts(Principal principal) {
    	ModelAndView mv = new ModelAndView();
    	Person loggedInUser = personService.findByUsername(principal.getName());
    	List<Post> posts = postService.getPostsByAuthorPersonId(loggedInUser.getPersonId());
    	mv.addObject("user",loggedInUser);
    	mv.addObject("posts",posts);
    	mv.setViewName("my-posts");
    	return mv;
    	
    }
    
    @GetMapping("/posts/{id}")
    public String viewPostDetails(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post-details";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model model, Principal principal) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.getAllCategories());
        String loggedInUser = principal.getName();
        model.addAttribute("user", personService.findByUsername(loggedInUser));
        model.addAttribute("tags", tagService.getAllTags());
        return "post-form";
    }

    @PostMapping("/posts/create")
    public String createPost(Post post, Principal principal) {
    	List<Tag> tags = post.getAsscTags().stream()
                .map(tag -> tagService.getTagById(tag.getTagId()))
                .toList();

        post.setAsscTags(tags);
        post.setAsscPostCategory(categoryService.getCategoryById(
                post.getAsscPostCategory().getCategoryId()
        ));
        String loggedInUser = principal.getName();
        post.setAuthor(personService.findByUsername(
                loggedInUser
        ));
        postService.createPost(post);
        return "redirect:/";
    }

    @GetMapping("/posts/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model, Principal principal) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryService.getAllCategories());
        String loggedInUser = principal.getName();
        model.addAttribute("user", personService.findByUsername(loggedInUser));
        model.addAttribute("tags", tagService.getAllTags());
        return "post-form";
    }

    @PostMapping("/posts/edit/{id}")
    public String updatePost(@PathVariable String id, Post post) {
        postService.updatePost(Long.parseLong(id), post);
        return "redirect:/";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }
}
