package com.planning.mealsandrecipes.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomErrorResponseTest {

    @Test
    void testGetSetTimestamp() {
        LocalDateTime expectedTimestamp = LocalDateTime.now();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setTimestamp(expectedTimestamp);
        assertEquals(expectedTimestamp, customErrorResponse.getTimestamp());
    }

    @Test
    void testGetSetStatus() {
        int expectedStatus = 404;
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setStatus(expectedStatus);
        assertEquals(expectedStatus, customErrorResponse.getStatus());
    }

    @Test
    void testGetSetError() {
        String expectedError = "Not Found";
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setError(expectedError);
        assertEquals(expectedError, customErrorResponse.getError());
    }


}

