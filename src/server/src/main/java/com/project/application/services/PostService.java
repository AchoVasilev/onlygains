package com.project.application.services;

import com.project.application.models.post.CreatePostResource;
import com.project.application.models.post.PostViewResource;
import com.project.common.Messages;
import com.project.domain.image.Image;
import com.project.domain.image.PostImage;
import com.project.domain.post.Post;
import com.project.domain.user.User;
import com.project.infrastructure.data.PostRepository;
import com.project.infrastructure.data.RoleRepository;
import com.project.infrastructure.data.UserRepository;
import com.project.infrastructure.exceptions.DuplicateEntryException;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.project.common.Constants.ITEMS_PER_PAGE;

@Singleton
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class.getName());
    private final PostRepository postRepository;
    private final CategoryService categoryService;

    //TODO: remove this one
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TagService tagService;

    public PostService(PostRepository postRepository, CategoryService categoryService, RoleRepository roleRepository, UserRepository userRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.categoryService = categoryService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.tagService = tagService;
    }

    @Transactional(readOnly = true)
    public List<PostViewResource> getNewest() {
        return this.postRepository.findNewestFour().stream().map(PostViewResource::of).toList();
    }

    @Transactional(readOnly = true)
    public Page<PostViewResource> getPostsBy(UUID categoryId, int page) {
        return this.postRepository.findByCategoryId(categoryId, Pageable.from(page, ITEMS_PER_PAGE)).map(PostViewResource::of);
    }

    @Transactional(readOnly = true)
    public List<PostViewResource> getMostPopularPosts() {
        return this.postRepository.getMostPopularPosts().stream().map(PostViewResource::of).toList();
    }

    @Transactional(readOnly = true)
    public Page<PostViewResource> getPostsByTag(UUID tagId, int page) {
        return this.postRepository.findPostsByTagId(tagId, Pageable.from(page, ITEMS_PER_PAGE))
                .map(PostViewResource::of);
    }

    @Transactional
    public void createPost(CreatePostResource postResource) {
        var postOpt = this.postRepository.findByTitleAndCategoryIdAndIsDeletedFalse(postResource.title(), postResource.categoryId());
        var postExists = this.postExists(postOpt, postResource);
        if (postExists) {
            postOpt.ifPresent(post -> {
                throw new DuplicateEntryException(String.format(Messages.ErrorMessages.DUPLICATE_POST, post.getTitle(), post.getId()));
            });
        }

        //TODO: mock user till auth
        var role = this.roleRepository.findById(UUID.fromString("ac3d9fd0-1725-466e-b0a7-00b9cd2161a7"));
        var user = new User("email@abv.bg", "somepwd", "Gosho", "Peshev", role.get());
        user.setImageUrl("https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691942376/onlygains/categories/hiking-trail-names_fgpox2.jpg");
        var category = this.categoryService.getCategoryBy(postResource.categoryId());
        var post = new Post(postResource.title(), postResource.text(), user, category);
        var images = postResource.imageUrls().stream().map(i -> new PostImage(i, post)).toList();
        post.addImagesToPost(images);
        user.addPost(post);

        var tags = this.tagService.getTags(postResource.tags());
        tags.forEach(post::addTag);

        this.userRepository.save(user);
        this.postRepository.save(post);
        log.info("Post has been created, [postId={}]", post.getId());
    }

    private boolean postExists(Optional<Post> postOptional, CreatePostResource postResource) {
        if (postOptional.isEmpty()) {
            return false;
        }

        var post = postOptional.get();
        var similarImageUrls = post.getPostImages().stream().map(Image::getUrl).toList().equals(postResource.imageUrls());
        return similarImageUrls && post.getCategory().getId().equals(postResource.categoryId()) && post.getTitle().equals(postResource.title()) && post.getText().equals(postResource.text());
    }
}
