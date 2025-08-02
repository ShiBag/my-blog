package com.shivam.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.blog.model.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {

}
