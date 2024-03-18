package com.restaurante.pedidoservice.dto;

import com.restaurante.pedidoservice.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
@Builder
public record OrderDto(@ApiModelProperty(hidden = true)
                       String id,
                       @Valid List<ItemsDto> items,
                       @ApiModelProperty(hidden = true)
                       BigDecimal totalValue,
                       @ApiModelProperty(hidden = true)
                       @Schema(description = "Status da ordem", example = "REQUESTED", implementation = StatusEnum.class)
                       StatusEnum status) {
}
