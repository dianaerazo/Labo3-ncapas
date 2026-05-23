package org.example.laboratorio3.domain.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {

    private String uri;

    private String message;

    private Integer status;

    private LocalDateTime time;

    private Object data;
}