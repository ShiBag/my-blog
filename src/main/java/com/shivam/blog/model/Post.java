package com.shivam.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String postName;
	
	@ManyToMany
	@JoinTable(
			name = "post_tags",
	        joinColumns = @JoinColumn(name = "post_id"),
	        inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	@JsonIgnore
	private List<Tag> asscTags;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category asscPostCategory;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person author;
	
	@Column(columnDefinition = "TEXT")
	private String postContent;
	
	@Column(updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
	
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public List<Tag> getAsscTags() {
		return asscTags;
	}
	public void setAsscTags(List<Tag> asscTags) {
		this.asscTags = asscTags;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public Person getAuthor() {
		return author;
	}
	public void setAuthor(Person author) {
		this.author = author;
	}
	public void setAsscPostCategory(Category asscPostCategory) {
		this.asscPostCategory = asscPostCategory;
	}
	public Category getAsscPostCategory() {
		return asscPostCategory;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "PostModel [postId=" + postId + ", postName=" + postName + ", asscPostCategory=" + asscPostCategory
				+ ", author=" + author + ", postContent=" + postContent + "]";
	}
	
	
	
	
	
	
}
