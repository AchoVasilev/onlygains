package com.project.posts.domain

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.UUID

@Repository
interface CategoryRepository : JpaRepository<Category, UUID>
