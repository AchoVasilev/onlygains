package com.project.domain.comment;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity(name = "likes")
public class Like extends BaseEntity {
    @Id
    private UUID id;

    @ManyToOne
    private Comment comment;
    protected Like() {}

    public Like(Comment comment) {
        super();
        this.comment = comment;
    }
}
