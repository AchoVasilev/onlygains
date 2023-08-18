package com.project.application.services;

import com.project.application.models.post.PostInitialViewResource;
import com.project.infrastructure.data.PostRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostInitialViewResource> getNewest() {
        return this.postRepository.findNewestFour()
                .stream().map(p -> new PostInitialViewResource(p.getId(),
                        p.getPostImages().get(0).getUrl(),
                        p.getCreatedAt().toString(),
                        p.getUser().getFullName(),
                        p.getTitle(), p.getText())).toList();
    }
}
