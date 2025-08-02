package com.shivam.blog.controller;

import com.shivam.blog.model.Category;
import com.shivam.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String viewCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories"; // Renders categories.html
    }
    
    @GetMapping("/{id}")
    public String viewCategoryDetails(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category-details";
    }
    
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/create")
    public String createCategory(Category category) {
        categoryService.createCategory(category);
        return "redirect:/";
    }

    @GetMapping("/categories/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "category-form";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        try {
            categoryService.deleteCategory(id);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return viewCategories(model);
        }
        return "redirect:/";
    }
}
