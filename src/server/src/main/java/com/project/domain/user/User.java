package com.project.domain.user;

import com.project.domain.BaseEntity;
import com.project.domain.image.UserImage;
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
    @OneToMany
    private List<UserImage> userImages;

    protected User() {
    }

    public User(String email, String password, String firstName, String lastName, Role role) {
        super();
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.fullName = FullName.of(firstName, lastName);
        this.role = role;
        this.userImages = new ArrayList<>();
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

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }
}
