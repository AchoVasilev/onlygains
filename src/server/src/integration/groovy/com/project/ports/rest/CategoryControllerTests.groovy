package com.project.ports.rest

import com.project.application.models.category.CategoryViewResource
import com.project.application.services.CategoryService
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class CategoryControllerTests extends Specification{
    @Inject
    CategoryService categoryService

    @Inject
    @Client("/")
    HttpClient client

    def "getCategories should return all categories"() {
        when: "getting the categories"
        def result = client.toBlocking().retrieve(HttpRequest.GET("/categories"), Argument.of(List.class, CategoryViewResource.class))

        then:
        result.size() != 0
    }
}
