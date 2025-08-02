package com.shivam.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.blog.model.Category;
import com.shivam.blog.repository.CategoryRepository;
import com.shivam.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow();
	}

	@Override
	public Category updateCategory(Long id, Category category) {
		return categoryRepository.findById(id).map(existingCategory -> {
			existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new RuntimeException("Category not found with id " + id));
	}

	@Override
	public void deleteCategory(Long id) {
		try {
	        Category category = categoryRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	        if (!category.getAsscPosts().isEmpty()) {
	            throw new RuntimeException("Cannot delete category with associated posts");
	        }

	        categoryRepository.delete(category);

	    } catch (RuntimeException e) {
	        throw new RuntimeException(e.getMessage()); // Re-throwing for controller to handle
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred while deleting the category");
	    }
	}
}
