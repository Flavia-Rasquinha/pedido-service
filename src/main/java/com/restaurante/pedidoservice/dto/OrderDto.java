package com.restaurante.pedidoservice.dto;

import com.restaurante.pedidoservice.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(Long id, List<ItemsDto> itens, BigDecimal totalValue, String status) {

    public OrderDto(OrderEntity order) {
        this(order.getId(), order.getItems(), order.getTotalValue(), order.getStatus());
    }
}
