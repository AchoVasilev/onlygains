package com.project.domain.user;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class User extends BaseEntity {
    @Id
    private UUID id;

    public User() {
        super();
        this.id = UUID.randomUUID();
    }
}
