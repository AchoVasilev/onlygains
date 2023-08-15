package com.project.domain;

import com.project.utilities.Time;
import jakarta.persistence.MappedSuperclass;

import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean isDeleted;

    protected BaseEntity() {
        this.createdAt = Time.utcNow();
        this.isDeleted = false;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = Time.utcNow();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
