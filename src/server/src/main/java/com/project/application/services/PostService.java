package com.project.application.services;

import com.project.application.models.post.CreatePostResource;
import com.project.application.models.post.PostInitialViewResource;
import com.project.common.Messages;
import com.project.domain.image.Image;
import com.project.domain.image.PostImage;
import com.project.domain.post.Post;
import com.project.domain.user.User;
import com.project.infrastructure.data.PostRepository;
import com.project.infrastructure.data.RoleRepository;
import com.project.infrastructure.data.UserRepository;
import com.project.infrastructure.exceptions.DuplicateEntryException;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class PostService {
    private final PostRepository postRepository;

    //TODO: remove this one
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<PostInitialViewResource> getNewest() {
        return this.postRepository.findNewestFour()
                .stream().map(p -> new PostInitialViewResource(p.getId(),
                        p.getPostImages().get(0).getUrl(),
                        p.getCreatedAt().toString(),
                        p.getUser().getFullName(),
                        p.getTitle(), p.getText())).toList();
    }

    @Transactional
    public void createPost(CreatePostResource postResource) {
        var postExists = this.postExists(this.postRepository
                .findByTitleAndCategoryIdAndIsDeletedFalse(postResource.title(), postResource.categoryId()), postResource);
        if (postExists) {
            throw new DuplicateEntryException(Messages.ErrorMessages.DUPLICATE_ENTRY);
        }


        //TODO: mock user till auth
        var role =  this.roleRepository.findById(UUID.fromString("ac3d9fd0-1725-466e-b0a7-00b9cd2161a7"));
        var user = new User("email@abv.bg", "somepwd", "Gosho", "Peshev", role.get());
        var post = new Post(postResource.title(), postResource.text(), postResource.categoryId(), user);
        var images = postResource.imageUrls().stream().map(i -> new PostImage(i, post)).toList();
        post.addImagesToPost(images);
        user.addPost(post);

        this.userRepository.save(user);
        this.postRepository.save(post);
    }

    private boolean postExists(Optional<Post> postOptional, CreatePostResource postResource) {
        if (postOptional.isEmpty()) {
            return false;
        }

        var post = postOptional.get();
        var similarImageUrls = post.getPostImages().stream().map(Image::getUrl).toList().equals(postResource.imageUrls());
        return similarImageUrls && post.getCategoryId() == postResource.categoryId() && post.getTitle().equals(postResource.title()) && post.getText().equals(postResource.text());
    }
}
