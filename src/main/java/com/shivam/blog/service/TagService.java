package com.shivam.blog.service;

import java.util.List;
import com.shivam.blog.model.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    Tag createTag(Tag tag);
    List<Tag> getAllTags();
    Tag getTagById(Long id);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);
}
