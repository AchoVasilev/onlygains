package com.project.infrastructure.data;

import com.project.domain.post.Post;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
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

    Page<Post> findByCategoryId(UUID categoryId, Pageable pageable);

    Optional<Post> findByTitleAndCategoryIdAndIsDeletedFalse(String title, UUID categoryId);

    @Query(value = """
            SELECT p.* FROM posts p
            LEFT JOIN comments c ON p.id = c.post_id
            GROUP BY p.id
            ORDER BY COUNT(c.id) DESC
            LIMIT 5
            """, nativeQuery = true)
    List<Post> getMostPopularPosts();

    @Query(value = "SELECT p FROM posts p JOIN tags t WHERE t.id = :tagId",
            countQuery = "SELECT COUNT(p.id) from posts JOIN tags t where t.id = :tagId")
    Page<Post> findPostsByTagId(UUID tagId, Pageable pageable);
}
