package com.project.application.services

import com.project.posts.application.CategoryService
import com.project.posts.domain.Category
import com.project.posts.domain.CategoryRepository
import spock.lang.Specification

class CategoryServiceTests extends Specification {
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

    def "get category by id should return correct object"() {
        given: "set up mocks"
        def category = new Category("category", "category", "url")
        1 * categoryRepository.findById(_) >> Optional.of(category)

        when: "service method is called"
        def result = categoryService.getCategoryBy(category.id)

        then: "objects match"
        result.id == category.id
        result.name == category.name
    }
}
