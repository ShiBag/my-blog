package com.shivam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.blog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
