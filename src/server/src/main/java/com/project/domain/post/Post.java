package com.project.domain.post;

import com.project.domain.BaseEntity;
import com.project.domain.category.Category;
import com.project.domain.comment.Comment;
import com.project.domain.image.PostImage;
import com.project.domain.user.User;
import com.project.utilities.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "posts")
public class Post extends BaseEntity {
    @Id
    private UUID id;
    private String title;
    private String text;
    @OneToMany
    private List<Comment> comments;
    @OneToMany(mappedBy = "post")
    @Cascade(CascadeType.ALL)
    private List<PostImage> postImages;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Post() {}

    public Post(String title, String text, User user, Category category) {
        super();
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
        this.category = category;
        this.comments = new ArrayList<>();
        this.postImages = new ArrayList<>();
        this.user = user;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public List<PostImage> getPostImages() {
        return this.postImages;
    }

    public void addImagesToPost(List<PostImage> postImages) {
        this.setModifiedAt(Time.utcNow());
        this.postImages.addAll(postImages);
    }

    public User getUser() {
        return this.user;
    }

    public Category getCategory() {
        return this.category;
    }
}
