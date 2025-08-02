package com.shivam.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivam.blog.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	Optional<Person> findByUsername(String username);
}
