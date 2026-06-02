package com.vasileva.calorizer.config;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class PostgresContainerTest {

    @Container
    private static final PostgreSQLContainer<?>
            POSTGRES = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @Test
    void testPostgresContainer() {
        try (PostgreSQLContainer<?>
                     postgres = new PostgreSQLContainer<>("postgres:17")) {
            postgres.start();

            System.out.println("Postgres URL: " + postgres.getJdbcUrl());
            System.out.println("Username: " + postgres.getUsername());
            System.out.println("Password: " + postgres.getPassword());
        }
    }
}
