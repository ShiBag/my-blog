package com.shivam.blog.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tagId;
	private String tagName;
	
	@ManyToMany(mappedBy="asscTags")
	@JsonIgnore
	private List<Post> asscPosts;
	
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public List<Post> getAsscPosts() {
		return asscPosts;
	}
	public void setAsscPosts(List<Post> asscPosts) {
		this.asscPosts = asscPosts;
	}
	@Override
	public String toString() {
		return "TagModel [tagId=" + tagId + ", tagName=" + tagName + "]";
	}
	
	
	
	
}
