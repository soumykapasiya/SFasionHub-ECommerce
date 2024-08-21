package com.kapasiya.fashionhub.exception.global;

import com.kapasiya.fashionhub.dto.response.CustomErrorResponseDto;
import com.kapasiya.fashionhub.exception.custom.*;
import com.kapasiya.fashionhub.utility.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CustomErrorResponseDto> userExceptionHandler(UserException ex, WebRequest request) {
        CustomErrorResponseDto errorResponseDto = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(errorResponseDto);
    }

    @ExceptionHandler(RoleException.class)
    public ResponseEntity<CustomErrorResponseDto> roleExceptionHandler(RoleException ex, WebRequest request) {
        CustomErrorResponseDto errorResponseDto = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(errorResponseDto);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<CustomErrorResponseDto> productExceptionHandler(ProductException ex, WebRequest request) {
        CustomErrorResponseDto errorResponseDto = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(errorResponseDto);
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<CustomErrorResponseDto> categoryExceptionHandler(CategoryException ex, WebRequest request) {
        CustomErrorResponseDto errorResponseDto = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(errorResponseDto);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<CustomErrorResponseDto> bookingExceptionHandler(BookingException ex, WebRequest request) {
        CustomErrorResponseDto errorResponseDto = ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false)
        );
        return ResponseEntity.ok().body(errorResponseDto);
    }
}