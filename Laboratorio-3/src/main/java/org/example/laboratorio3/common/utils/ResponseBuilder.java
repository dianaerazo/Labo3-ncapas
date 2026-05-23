package org.example.laboratorio3.common.utils;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.example.laboratorio3.domain.dto.response.GeneralResponse;

@Component
public class ResponseBuilder {

    public ResponseEntity<GeneralResponse> buildResponse(
            String uri,
            String message,
            HttpStatus status,
            Object data
    ) {
        GeneralResponse response = GeneralResponse.builder()
                .uri(uri)
                .message(message)
                .status(status.value())
                .time(LocalDateTime.now())
                .data(data)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}