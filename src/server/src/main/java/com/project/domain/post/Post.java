package com.project.domain.post;

import com.project.domain.BaseEntity;
import com.project.domain.category.Category;
import com.project.domain.comment.Comment;
import com.project.domain.image.PostImage;
import com.project.domain.user.User;
import com.project.utilities.Time;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity(name = "posts")
public class Post extends BaseEntity {
    @Id
    private UUID id;
    private String title;
    private String text;
    private String previewText;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PostImage> postImages;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "posts_tags", joinColumns = @JoinColumn(name = "post_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private final Set<Tag> tags;

    protected Post() {
        super();
        this.comments = new ArrayList<>();
        this.postImages = new ArrayList<>();
        this.tags = new HashSet<>();
    }

    public Post(String title, String text, String previewText, User user, Category category) {
        this();
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
        this.previewText = previewText;
        this.category = category;
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

    public String getPreviewText() {
        return this.previewText;
    }

    public User getUser() {
        return this.user;
    }

    public Category getCategory() {
        return this.category;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.addPost(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.removePost(this);
    }
}
