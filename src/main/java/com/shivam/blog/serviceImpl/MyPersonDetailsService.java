package com.shivam.blog.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shivam.blog.model.Person;
import com.shivam.blog.model.PersonPrincipal;
import com.shivam.blog.repository.PersonRepository;

@Service
public class MyPersonDetailsService implements UserDetailsService {

	@Autowired
    private PersonRepository personRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Person person = personRepository.findByUsername(username).orElse(null);
		if(person==null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
		
		return new PersonPrincipal(person);
	}

}
