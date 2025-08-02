package com.shivam.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.blog.model.Tag;
import com.shivam.blog.repository.TagRepository;
import com.shivam.blog.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + id));
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        return tagRepository.findById(id).map(existingTag -> {
            existingTag.setTagName(tag.getTagName());
            return tagRepository.save(existingTag);
        }).orElseThrow(() -> new RuntimeException("Tag not found with id " + id));
    }

    @Override
    public void deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new RuntimeException("Tag not found with id " + id);
        }
        tagRepository.deleteById(id);
    }
}
