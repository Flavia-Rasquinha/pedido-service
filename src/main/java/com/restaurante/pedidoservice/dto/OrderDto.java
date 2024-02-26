package com.restaurante.pedidoservice.dto;

import com.restaurante.pedidoservice.enums.StatusEnum;
import lombok.Builder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
@Builder
public record OrderDto(@Valid List<ItemsDto> items, BigDecimal totalValue, StatusEnum status) {

}
