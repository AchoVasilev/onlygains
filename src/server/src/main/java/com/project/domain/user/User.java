package com.project.domain.user;

import com.project.domain.BaseEntity;
import com.project.domain.comment.Comment;
import com.project.domain.post.Post;
import com.project.domain.valueobjects.FullName;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
public class User extends BaseEntity {
    @Id
    private UUID id;
    private String email;
    private String password;
    @Embedded
    private FullName fullName;
    @ManyToOne
    private Role role;
    private String imageUrl;
    @OneToMany(mappedBy = "user")
    private final List<Post> posts;

    @OneToMany(mappedBy = "user")
    private final List<Comment> comments;

    protected User() {
        super();
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public User(String email, String password, String firstName, String lastName, Role role) {
        this();
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.fullName = FullName.from(firstName, lastName);
        this.role = role;
    }

    public UUID getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFullName() {
        return this.fullName.getFullName();
    }

    public String getFirstName() {
        return this.fullName.getFirstName();
    }

    public String getLastName() {
        return this.fullName.getLastName();
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public List<Post> getPosts() {
        return this.posts;
    }
}
