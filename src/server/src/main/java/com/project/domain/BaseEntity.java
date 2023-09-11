package com.project.domain;

import com.project.utilities.Time;
import jakarta.persistence.MappedSuperclass;

import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private final ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
    private boolean isDeleted;

    protected BaseEntity() {
        this.createdAt = Time.utcNow();
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setUpdatedAt() {
        this.modifiedAt = Time.utcNow();
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setIsDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public boolean getIsDeleted() {
        return this.isDeleted;
    }
}
