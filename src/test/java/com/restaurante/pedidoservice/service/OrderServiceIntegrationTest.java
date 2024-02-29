package com.restaurante.pedidoservice.service;

import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.enums.StatusEnum;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.producer.Producer;
import com.restaurante.pedidoservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureDataMongo
@SpringBootTest
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void findOrderByIdSucess() {
        orderRepository.save(OrderEntity.builder().id("1").status(StatusEnum.REQUESTED).build());
        OrderDto orderDto = orderService.getOrderById("1");

        Assertions.assertEquals(StatusEnum.REQUESTED, orderDto.status());
    }

    @Test
    public void deleteOrderByIdSucess() {
        orderRepository.save(OrderEntity.builder().id("1").status(StatusEnum.REQUESTED).build());
        orderService.deleteOrder("1");

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrderById("1");
        });
    }

    @Test
    public void updateOrderByIdSucess() {
        orderRepository.save(OrderEntity.builder().id("1").status(StatusEnum.REQUESTED).build());
        OrderDto orderDto = orderService.updateOrder("1", StatusEnum.READY);

        Assertions.assertEquals(StatusEnum.READY, orderDto.status());
    }
}