package com.restaurante.pedidoservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
public record ItemsDto(@JsonInclude(JsonInclude.Include.NON_NULL) Long id,
                       @JsonInclude(JsonInclude.Include.NON_NULL) String idIngredient,
                       @JsonInclude(JsonInclude.Include.NON_NULL) String idDish,
                       @NotNull(message = "[amount] field te cannot be null") Integer amount,
                       @NotNull(message = "[value] field te cannot be null") BigDecimal value) {

}
