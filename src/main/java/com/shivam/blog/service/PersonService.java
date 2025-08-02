package com.shivam.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shivam.blog.model.Person;

@Service
public interface PersonService {
	Person createPerson(Person person);
	List<Person> getAllPersons();
	Person getPersonById(Long id);
	Person updatePerson(Long id, Person person);
	void deletePerson(Long id);
	Person findByUsername(String username);
}
