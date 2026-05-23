package org.example.laboratorio3.domain.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private String uri;

    private String message;

    private Integer status;

    private LocalDateTime time;

    private List<String> errors;
}