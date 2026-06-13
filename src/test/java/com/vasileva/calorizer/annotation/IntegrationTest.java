package com.vasileva.calorizer.annotation;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@Import(IntegrationTest.PostgresContainerTest.class)
@Testcontainers
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})
public @interface IntegrationTest {

    @TestConfiguration
    class PostgresContainerTest {

        public static final String POSTGRES_CONTAINER_NAME = "postgres.container.name";
        public static final String DEFAULT_POSTGRES = "postgres:17";

        @Bean
        @ServiceConnection
        public PostgreSQLContainer<?> postgresContainer(Environment environment) {
            String postgresContainerName = environment.getProperty(POSTGRES_CONTAINER_NAME, DEFAULT_POSTGRES);
            return new PostgreSQLContainer<>(postgresContainerName);
        }
    }

}
