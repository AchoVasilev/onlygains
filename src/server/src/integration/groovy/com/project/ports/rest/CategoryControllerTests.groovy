package com.project.ports.rest

import com.project.BaseIntegrationTest
import com.project.posts.application.models.category.CategoryViewResource
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest

class CategoryControllerTests extends BaseIntegrationTest {

    def "Getting all categories"() {
        when: "getting response"
        def result = httpClient.toBlocking().retrieve(HttpRequest.GET("/categories"), Argument.of(List.class, CategoryViewResource.class))

        then: "list is not empty"
        result.size > 0
        result.each { it -> it instanceof CategoryViewResource }
    }
}
