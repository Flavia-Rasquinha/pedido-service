package com.restaurante.pedidoservice.dto;

import com.restaurante.pedidoservice.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
@Builder
public record OrderDto(@Valid List<ItemsDto> items,
                       @Schema(description = "Valor total", example = "10.0", implementation = BigDecimal.class)
                       BigDecimal totalValue,
                       @Schema(description = "Status da ordem", example = "REQUESTED", implementation = StatusEnum.class)

                       StatusEnum status) {

}
