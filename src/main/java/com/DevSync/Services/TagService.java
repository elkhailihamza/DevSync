package com.DevSync.Services;

import com.DevSync.Entities.Tags;
import com.DevSync.Repositories.TagRepository;

import java.util.List;

public class TagService {
    public TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tags findById(long id) {
        return tagRepository.findById(id);
    }

    public List<Tags> fetchAll() {
        return tagRepository.fetchAll();
    }

    public void save(Tags entity) {
        tagRepository.save(entity);
    }

    public void update (Tags entity) {
        tagRepository.update(entity);
    }

    public void delete(Tags entity) {
        tagRepository.delete(entity);
    }
}
