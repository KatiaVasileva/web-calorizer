package com.vasileva.calorizer.database;

import com.vasileva.calorizer.config.PostgresContainerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
public class DatabaseIntegrationTest extends PostgresContainerTest {

    @Test
    void contextLoads() {
        // Ваш тест здесь
    }
}
