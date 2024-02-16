package com.restaurante.pedidoservice.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemsDto(Long id, String idIngredient,String idDish, int amount, BigDecimal value) {

}
