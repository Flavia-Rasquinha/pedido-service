package com.restaurante.pedidoservice.entity;

import com.restaurante.pedidoservice.dto.ItemsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Document(collection = "Pedido")
public class OrderEntity {

    @Id
    private Long id;
    @Field(name = "Items")
    private List<ItemsDto> items;
    @Field(name = "Valor_Total")
    private BigDecimal totalValue;
    @Field(name = "Status")
    private String status;

}
