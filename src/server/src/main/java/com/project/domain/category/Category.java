package com.project.domain.category;

import com.project.domain.BaseEntity;
import com.project.domain.image.CategoryImage;
import com.project.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "categories")
public class Category extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    @OneToMany
    private List<Post> posts;
    @OneToMany
    private List<CategoryImage> categoryImages;

    protected Category() {
    }

    public Category(String name) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.posts = new ArrayList<>();
        this.categoryImages = new ArrayList<>();
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<CategoryImage> getCategoryImages() {
        return categoryImages;
    }
}
