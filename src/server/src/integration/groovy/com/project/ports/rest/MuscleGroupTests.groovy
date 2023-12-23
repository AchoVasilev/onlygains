package com.project.ports.rest

import com.project.BaseIntegrationTest
import com.project.application.models.exercise.MuscleGroupDetailsResource
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest

class MuscleGroupTests extends BaseIntegrationTest {
    def "Getting all muscle groups"() {
        when: "a request is made"
        def result = httpClient.toBlocking().retrieve(HttpRequest.GET("/muscle-groups"), Argument.of(List.class, MuscleGroupDetailsResource.class))

        then: "result is not empty"
        result.size > 0
        result.each {it -> it instanceof MuscleGroupDetailsResource}
    }
}
