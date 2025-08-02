package com.shivam.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.blog.model.Person;
import com.shivam.blog.repository.PersonRepository;
import com.shivam.blog.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person findByUsername(String username) {
		return personRepository.findByUsername(username).orElseThrow();
	}
	
	@Override
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@Override
	public Person getPersonById(Long id) {
		return personRepository.findById(id).orElseThrow();
	}

	@Override
	public Person updatePerson(Long id, Person person) {
		return personRepository.findById(id).map(existingPerson -> {
            existingPerson.setName(person.getName());
            existingPerson.setUsername(person.getUsername());
            existingPerson.setPassword(person.getPassword());
            return personRepository.save(existingPerson);
        }).orElseThrow(() -> new RuntimeException("Person not found with id " + id));
	}

	@Override
	public void deletePerson(Long id) {
		if (!personRepository.existsById(id)) {
            throw new RuntimeException("Person not found with id " + id);
        }
        personRepository.deleteById(id);	
	}
}
