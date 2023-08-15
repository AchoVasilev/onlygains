package com.project.infrastructure.data;

import com.project.domain.post.Post;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {
    @Override
    @Query("UPDATE post SET is_deleted = false WHERE id = :id")
    void deleteById(UUID id);

    @Query("SELECT * from post p WHERE p.is_deleted = false ORDER BY p.createdAt LIMIT 4")
    List<Post> findNewest();
}
