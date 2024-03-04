package com.project.posts.infrastructure

import com.project.posts.domain.Category
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.UUID

@Repository
interface CategoryRepository : JpaRepository<Category, UUID>
