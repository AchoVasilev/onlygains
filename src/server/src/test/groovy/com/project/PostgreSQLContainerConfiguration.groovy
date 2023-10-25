package com.project

import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.PostgreSQLContainer

abstract class PostgreSQLContainerConfiguration implements TestPropertyProvider {
    static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:12")
            .withDatabaseName("databasename")
            .withUsername("myuser")
            .withPassword("mypassword")

    static {
        postgreSQLContainer.start()
    }

    @Override
    Map<String, String> getProperties() {
        postgreSQLContainer.start();
        def properties = Map.of("datasources.default.driverClassName", postgreSQLContainer.getDriverClassName(),
        "datasources.default.username", postgreSQLContainer.getUsername(), "datasources.default.password", postgreSQLContainer.password,
        "datasources.default.url", postgreSQLContainer.getJdbcUrl())

        return properties
    }
}
