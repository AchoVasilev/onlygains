package com.project.infrastructure.data

import com.project.domain.post.Post
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.CrudRepository
import java.util.Optional
import java.util.UUID

@Repository
interface PostRepository : CrudRepository<Post, UUID> {
    @Query("UPDATE posts SET is_deleted = true WHERE id = :id")
    override fun deleteById(id: UUID)

    @Query(value = "SELECT * from posts p WHERE p.is_deleted = false ORDER BY p.created_at DESC LIMIT 4", nativeQuery = true)
    fun findNewestFour(): List<Post>

    fun findByCategoryId(categoryId: UUID, pageable: Pageable): Page<Post>

    fun findByTitleAndCategoryIdAndIsDeletedFalse(title: String, categoryId: UUID): Optional<Post>

    @Query(value = """
            SELECT p.* FROM posts p
            LEFT JOIN comments c ON p.id = c.post_id
            GROUP BY p.id
            ORDER BY COUNT(c.id) DESC
            LIMIT 5
            
            """, nativeQuery = true)
    fun findMostPopularPosts(): List<Post>

    @Query(value = "SELECT p FROM posts p JOIN tags t WHERE t.id = :tagId", countQuery = "SELECT COUNT(p.id) FROM posts p JOIN tags t WHERE t.id = :tagId")
    fun findPostsByTagId(tagId: UUID, pageable: Pageable): Page<Post>
}
