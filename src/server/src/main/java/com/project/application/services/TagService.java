package com.project.application.services;

import com.project.application.models.tag.TagViewResource;
import com.project.infrastructure.data.TagRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagViewResource> getTags() {
        return this.tagRepository.findAll()
                .stream().map(t -> new TagViewResource(t.getId(), t.getName()))
                .toList();
    }
}
