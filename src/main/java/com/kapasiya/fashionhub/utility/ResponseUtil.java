package com.kapasiya.fashionhub.utility;

import com.kapasiya.fashionhub.dto.response.CustomErrorResponseDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class ResponseUtil {
    private ResponseUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static CustomErrorResponseDto buildErrorResponse(HttpStatus status, String message, String endpoint) {
        return CustomErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now())
                .message(message)
                .status(status.value())
                .endpoint(endpoint)
                .build();
    }
}
