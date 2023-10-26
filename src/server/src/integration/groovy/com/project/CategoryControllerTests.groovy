package com.project

import com.project.application.models.category.CategoryViewResource
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
    @Client("/")
    HttpClient httpClient

    def "first"() {
        when: "get response"
        def res = httpClient.toBlocking().retrieve(HttpRequest.GET("/categories"), Argument.of(List.class, CategoryViewResource.class))
        def s = res
        then: "something"
    }
}
