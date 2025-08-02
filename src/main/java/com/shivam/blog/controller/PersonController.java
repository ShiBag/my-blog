package com.shivam.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.blog.model.Person;
import com.shivam.blog.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person savedPerson = personService.createPerson(person);
		return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);	
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> getAllPersons(){
		List<Person> persons = personService.getAllPersons();
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id){
		try {
			Person person = personService.getPersonById(id);
			return new ResponseEntity<>(person, HttpStatus.OK);
		} catch (RuntimeException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }   
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person){
		try {
            Person updatedPerson = personService.updatePerson(id, person);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePerson(@PathVariable Long id){
		try {
			personService.deletePerson(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
