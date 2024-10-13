package com.DevSync.Controllers;

import com.DevSync.Entities.Tags;
import jakarta.enterprise.context.RequestScoped;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class TagController extends Controller {
    public void saveTag(Tags tag) {
        tagService.save(tag);
    }

    public void saveTagList(List<Tags> tags) {
        tagService.saveTagList(tags);
    }

    public List<Tags> returnAsTags(String[] tagStringArr) {
        return Arrays.stream(tagStringArr)
                .map(this::createTag)
                .collect(Collectors.toList());
    }

    private Tags createTag(String tagName) {
        Tags tag = new Tags();
        tag.setTag_name(tagName);
        return tag;
    }

    public Tags findByName(String tagName) {
        return tagService.fetchByName(tagName);
    }
}
