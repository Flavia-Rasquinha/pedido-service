package com.restaurante.pedidoservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record ItemsDto(@JsonInclude(JsonInclude.Include.NON_NULL)
                       Long id,
                       @JsonInclude(JsonInclude.Include.NON_NULL)
                       @Schema(description = "Id do produto", example = "1", implementation = String.class)
                       String idProduct,
                       @NotNull(message = "[amount] field te cannot be null")
                       @Schema(description = "Quantidade", example = "1", implementation = Integer.class)
                       Integer amount,
                       @NotNull(message = "[value] field te cannot be null")
                       @Schema(description = "Valor do item", example = "10.0", implementation = BigDecimal.class)
                       BigDecimal value) {

}
