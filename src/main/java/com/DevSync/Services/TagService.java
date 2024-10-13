package com.DevSync.Services;

import com.DevSync.Entities.Tags;
import com.DevSync.Repositories.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TagService {

    @Inject
    private TagRepository tagRepository;

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

    public Tags fetchByName(String name) {
        return tagRepository.fetchByName(name);
    }

    public void saveTagList(List<Tags> tags) {
        tags.forEach(this::save);
    }
}
