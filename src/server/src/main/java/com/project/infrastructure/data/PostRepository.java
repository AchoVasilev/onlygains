package com.project.infrastructure.data;

import com.project.domain.post.Post;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {
    @Override
    @Query("UPDATE posts SET is_deleted = true WHERE id = :id")
    void deleteById(UUID id);

    @Query(value = "SELECT * from posts p WHERE p.is_deleted = false ORDER BY p.created_at DESC LIMIT 4", nativeQuery = true)
    List<Post> findNewestFour();

    Optional<Post> findByTitleAndCategoryIdAndIsDeletedFalse(String title, UUID categoryId);
}
