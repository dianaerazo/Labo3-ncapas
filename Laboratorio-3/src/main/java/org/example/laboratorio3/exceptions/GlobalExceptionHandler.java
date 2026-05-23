package org.example.laboratorio3.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.example.laboratorio3.domain.dto.response.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;




    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(
                ResourceNotFoundException exception,
                HttpServletRequest request
        ) {
            return buildErrorResponse(
                    request.getRequestURI(),
                    exception.getMessage(),
                    HttpStatus.NOT_FOUND,
                    List.of(exception.getMessage())
            );
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(
                MethodArgumentNotValidException exception,
                HttpServletRequest request
        ) {
            List<String> errors = exception.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .toList();

            return buildErrorResponse(
                    request.getRequestURI(),
                    "Validation failed",
                    HttpStatus.BAD_REQUEST,
                    errors
            );
        }

        private ResponseEntity<ApiErrorResponse> buildErrorResponse(
                String uri,
                String message,
                HttpStatus status,
                List<String> errors
        ) {
            ApiErrorResponse response = ApiErrorResponse.builder()
                    .uri(uri)
                    .message(message)
                    .status(status.value())
                    .time(LocalDateTime.now())
                    .errors(errors)
                    .build();

            return ResponseEntity.status(status).body(response);
        }
    }