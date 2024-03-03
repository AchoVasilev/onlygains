package com.project.ports.rest

import com.project.BaseIntegrationTest
import com.project.posts.application.models.tag.TagViewResource
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest

class TagControllerTests extends BaseIntegrationTest {
    def "Getting all tags"() {
        when: "getting response"
        def result = httpClient.toBlocking().retrieve(HttpRequest.GET("/categories"), Argument.of(List.class, TagViewResource.class))

        then: "list is not empty"
        result.size > 0
        result.each { it -> it instanceof TagViewResource }
    }
}
