package com.vishwas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SpringBootJUnitTestingApplicationTest {
    @Test
    void testMainMethod() {
        // Arrange
        String[] args = {};

        // Act & Assert
        assertDoesNotThrow(() ->
                SpringBootJUnitTestingApplication.main(args)
        );
    }
}
