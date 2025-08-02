package com.shivam.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shivam.blog.model.Post;

@Service
public interface PostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
	List<Post> getAllPostsSortedByDate();
	List<Post> getPostsByAuthorPersonId(Long personId);
}
