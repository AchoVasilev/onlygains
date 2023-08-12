package com.project.domain;

import com.project.utilities.Time;
import jakarta.persistence.MappedSuperclass;

import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    protected BaseEntity() {
        this.createdAt = Time.utcNow();
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
}
