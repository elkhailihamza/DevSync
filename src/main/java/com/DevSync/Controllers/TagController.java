package com.DevSync.Controllers;

import com.DevSync.Entities.Tag;
import jakarta.enterprise.context.RequestScoped;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class TagController extends Controller {
    public void saveTag(Tag tag) {
        tagService.save(tag);
    }

    public void saveTagList(List<Tag> tags) {
        tagService.saveTagList(tags);
    }

    public List<Tag> returnAsTags(String[] tagStringArr) {
        return Arrays.stream(tagStringArr)
                .map(this::createTag)
                .collect(Collectors.toList());
    }

    private Tag createTag(String tagName) {
        Tag tag = new Tag();
        tag.setTag_name(tagName);
        return tag;
    }

    public Tag findByName(String tagName) {
        return tagService.fetchByName(tagName);
    }
}
