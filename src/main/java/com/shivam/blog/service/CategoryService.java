package com.shivam.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shivam.blog.model.Category;

@Service
public interface CategoryService {
	Category createCategory(Category category);
	List<Category> getAllCategories();
	Category getCategoryById(Long id);
	Category updateCategory(Long id, Category category);
	void deleteCategory(Long id);
}
