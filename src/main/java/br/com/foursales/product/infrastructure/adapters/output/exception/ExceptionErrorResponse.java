package br.com.foursales.product.infrastructure.adapters.output.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionErrorResponse {

    private LocalDateTime date;

    private String message;

    private List<String> details;

}