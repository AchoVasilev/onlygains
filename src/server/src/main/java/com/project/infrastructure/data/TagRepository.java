package com.project.infrastructure.data;

import com.project.domain.post.Tag;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    List<Tag> findByIdIn(List<UUID> ids);
}
