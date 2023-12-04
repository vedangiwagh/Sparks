package com.planning.mealsandrecipes.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class GlobalExceptionHandlerTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Mock
    private ResourceNotFoundException mockException;

    @Mock
    private WebRequest mockRequest;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testResourceNotFoundExceptionHandler() {
        // Arrange
        String errorMessage = "Resource not found";
        Mockito.when(mockException.getMessage()).thenReturn(errorMessage);

        // Act
        ResponseEntity<CustomErrorResponse> responseEntity = globalExceptionHandler.globalExceptionHandler(mockException, mockRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus());
        assertEquals(errorMessage, responseEntity.getBody().getError());
        assertEquals(LocalDateTime.class, responseEntity.getBody().getTimestamp().getClass());
    }

}

