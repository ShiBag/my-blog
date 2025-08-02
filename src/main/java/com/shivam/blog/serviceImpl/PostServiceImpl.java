package com.shivam.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shivam.blog.model.Post;
import com.shivam.blog.repository.PostRepository;
import com.shivam.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    @Override
    public List<Post> getPostsByAuthorPersonId(Long personId) {
        return postRepository.findByAuthorPersonId(personId);
    }
    
    @Override
    public List<Post> getAllPostsSortedByDate() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public Post updatePost(Long id, Post post) {
        return postRepository.findById(id).map(existingPost -> {
            existingPost.setPostName(post.getPostName());
            existingPost.setPostContent(post.getPostContent());
            existingPost.setAsscPostCategory(post.getAsscPostCategory());
            existingPost.setAuthor(post.getAuthor());
            existingPost.setAsscTags(post.getAsscTags());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found with id " + id);
        }
        postRepository.deleteById(id);
    }
}
