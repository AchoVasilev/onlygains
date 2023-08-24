package com.project.domain.category;

import com.project.domain.BaseEntity;
import com.project.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "categories")
public class Category extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    private String translatedName;
    @OneToMany(mappedBy = "category")
    @Cascade(value = CascadeType.ALL)
    private List<Post> posts;
    private String imageUrl;

    protected Category() {
    }

    public Category(String name, String translatedName, String imageUrl) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.translatedName = translatedName;
        this.posts = new ArrayList<>();
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTranslatedName() {
        return translatedName;
    }
}
