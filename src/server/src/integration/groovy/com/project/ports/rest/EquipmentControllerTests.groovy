package com.project.ports.rest

import com.project.BaseIntegrationTest
import com.project.application.models.exercise.EquipmentResource
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest

class EquipmentControllerTests extends BaseIntegrationTest {

    def "Getting all equipment"() {
        when: "getting response"
        def result = httpClient.toBlocking().retrieve(HttpRequest.GET("/categories"), Argument.of(List.class, EquipmentResource.class))

        then: "list is not empty"
        result.size() > 0
        result.each { it -> it instanceof EquipmentResource }
    }
}
