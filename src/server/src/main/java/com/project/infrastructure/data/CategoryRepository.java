package com.project.infrastructure.data;

import com.project.domain.category.Category;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {
}
