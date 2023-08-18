package com.project

import com.project.application.services.CategoryService
import com.project.domain.category.Category
import com.project.infrastructure.data.CategoryRepository
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class CategoryServiceTests extends Specification{
    CategoryRepository categoryRepository = Mock(CategoryRepository)
    CategoryService categoryService = new CategoryService(categoryRepository)

    def "get categories should call repository and return correct count"() {
        given: "set up mocks"
        1 * categoryRepository.findAll() >> List.of(new Category("category", "category", "url"))

        when: "service method is called"
        def categories = categoryService.getCategories()

        then: "size is correct"
        categories.size() == 1
    }
}
