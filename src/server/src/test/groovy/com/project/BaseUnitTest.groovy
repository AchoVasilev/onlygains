package com.project

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@MicronautTest
@Testcontainers
abstract class BaseUnitTest extends Specification {
    @Shared
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("databasename")
            .withUsername("myuser")
            .withPassword("mypassword")
            .withReuse(true)
}
