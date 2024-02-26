package com.restaurante.pedidoservice.entity;

import com.restaurante.pedidoservice.dto.ItemsDto;
import com.restaurante.pedidoservice.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
@Document(collection = "Pedido")
public class OrderEntity {

    @Id
    private String id;
    @Field(name = "Items")
    private List<ItemsDto> items;
    @Field(name = "Valor_Total")
    private BigDecimal totalValue;
    @Field(name = "Status")
    private StatusEnum status;

}
