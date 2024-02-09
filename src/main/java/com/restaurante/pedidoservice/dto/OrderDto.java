package com.restaurante.pedidoservice.dto;

import com.restaurante.pedidoservice.entity.OrderEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
@Builder
public record OrderDto(List<ItemsDto> items, BigDecimal totalValue, String status) {

    public OrderDto(OrderEntity order) {
        this(order.getItems(), order.getTotalValue(), order.getStatus());
    }
}
