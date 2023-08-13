package com.project.domain.image;

import com.project.domain.BaseEntity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class Image extends BaseEntity {
    @Id
    private UUID id;
    private String url;
    protected Image() {
    }

    protected Image(String url) {
        super();
        this.id = UUID.randomUUID();
        this.url = url;
    }

    public UUID getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
