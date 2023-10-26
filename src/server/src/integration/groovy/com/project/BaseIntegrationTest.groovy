package com.project

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class BaseIntegrationTest extends Specification{
    @Inject
    @Client("/")
    HttpClient httpClient
}
