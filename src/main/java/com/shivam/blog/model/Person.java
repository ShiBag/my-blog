package com.shivam.blog.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personId;
	private String name;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	
	@OneToMany(mappedBy="author", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> asscPosts;
	
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Post> getAsscPosts() {
		return asscPosts;
	}
	public void setAsscPosts(List<Post> asscPosts) {
		this.asscPosts = asscPosts;
	}
	
	@Override
	public String toString() {
		return "PersonModel [personId=" + personId + ", name=" + name + ", username=" + username + ", password="
				+ password + "]";
	}
	
	
	
	
}
