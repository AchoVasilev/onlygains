package com.project.domain.post;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tags")
public class Tag extends BaseEntity {
    @Id
    private UUID id;
    private String name;
    private String translatedName;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts;

    protected Tag() {}

    public Tag(String name, String translatedName) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
        this.translatedName = translatedName;
        this.posts = new HashSet<>();
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTranslatedName() {
        return this.translatedName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void removePost(Post post) {
        this.posts.remove(post);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
