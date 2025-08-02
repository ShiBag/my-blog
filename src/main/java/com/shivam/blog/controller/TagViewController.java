package com.shivam.blog.controller;

import com.shivam.blog.model.Tag;
import com.shivam.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagViewController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public String viewTags(Model model) {
        model.addAttribute("tags", tagService.getAllTags());
        return "tags"; // Renders tags.html
    }
    
    @GetMapping("/{id}")
    public String viewTagDetails(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "tag-details";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag-form";
    }

    @PostMapping("/create")
    public String createTag(Tag tag) {
        tagService.createTag(tag);
        return "redirect:/tags";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "tag-form";
    }

    @PostMapping("/update/{id}")
    public String updateTag(@PathVariable Long id, Tag tag) {
        tagService.updateTag(id, tag);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/";
    }
}
