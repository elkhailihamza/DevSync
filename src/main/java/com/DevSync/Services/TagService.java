package com.DevSync.Services;

import com.DevSync.Entities.Tag;
import com.DevSync.Repositories.TagRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag findById(long id) {
        return tagRepository.findById(id);
    }

    public List<Tag> fetchAll() {
        return tagRepository.fetchAll();
    }

    public void save(Tag entity) {
        tagRepository.save(entity);
    }

    public void update (Tag entity) {
        tagRepository.update(entity);
    }

    public void delete(Tag entity) {
        tagRepository.delete(entity);
    }

    public Tag fetchByName(String name) {
        return tagRepository.fetchByName(name);
    }

    public void saveTagList(List<Tag> tags) {
        tags.forEach(this::save);
    }
}
