package com.project.domain.user;

import com.project.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "roles")
public class Role extends BaseEntity {
    @Id
    private UUID id;
    private String name;

    protected Role () {}

    public Role(String name) {
        super();
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
