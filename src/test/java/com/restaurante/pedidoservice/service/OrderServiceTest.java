package com.restaurante.pedidoservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.pedidoservice.dto.ItemsDto;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.producer.Producer;
import com.restaurante.pedidoservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private Producer producer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrderSuccess() throws JsonProcessingException {

        Mockito.when(orderRepository.save(any())).thenReturn(OrderEntity.builder().id("1").status("PRONTO").build());
        Mockito.when(objectMapper.convertValue(any(), eq(OrderEntity.class))).thenReturn(OrderEntity.builder()
                .items(Collections.singletonList(ItemsDto.builder().value(BigDecimal.TEN).build())).status("PRONTO").build());
        Mockito.when(objectMapper.convertValue(any(), eq(OrderDto.class))).thenReturn(OrderDto.builder().status("PRONTO").build());
        OrderDto orderDto = createOrder();

        OrderDto createdOrderDto = orderService.createOrder(orderDto);

        Assertions.assertEquals("PRONTO", createdOrderDto.status());
    }

    @Test
    public void createOrderError() {
        Mockito.when(orderRepository.save(any())).thenThrow(NullPointerException.class);

        Assertions.assertThrows(NullPointerException.class, () -> {
            orderService.createOrder(null);
        });
    }

    @Test
    public void updateOrderSuccess() {

        Mockito.when(orderRepository.save(any())).thenReturn(OrderEntity.builder().id("1").status("PRONTO").build());
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(OrderEntity.builder().id("1").status("PRONTO").build()));
        Mockito.when(objectMapper.convertValue(any(), eq(OrderDto.class))).thenReturn(OrderDto.builder().status("PRONTO").build());

        OrderDto createdOrderDto = orderService.updateOrder("1", "PRONTO");

        Assertions.assertEquals("PRONTO", createdOrderDto.status());
    }

    @Test
    public void updateOrderError() {
        Mockito.when(orderRepository.save(any())).thenThrow(OrderNotFoundException.class);

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.updateOrder("1", "PRONTO");
        });
    }

    @Test
    public void getOrderByIdSuccess() {

        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(OrderEntity.builder().status("PRONTO").build()));
        Mockito.when(objectMapper.convertValue(any(), eq(OrderDto.class))).thenReturn(OrderDto.builder().status("PRONTO").build());

        OrderDto createdOrderDto = orderService.getOrderById("1");

        Assertions.assertEquals("PRONTO", createdOrderDto.status());
    }

    @Test
    public void getOrderByIdError() {
        Mockito.when(orderRepository.save(any())).thenThrow(OrderNotFoundException.class);

        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.getOrderById("1");
        });
    }

    @Test
    public void getOrderSuccess() {

        List<OrderEntity> mockOrder = Arrays.asList(
                new OrderEntity("1",Collections.singletonList(ItemsDto.builder()
                        .amount(1).build()),BigDecimal.ONE, "Solicitado"),
                new OrderEntity("2",Collections.singletonList(ItemsDto.builder()
                        .amount(1).build()),BigDecimal.ONE, "Solicitado")
        );
        Mockito.when(orderRepository.findAll()).thenReturn(mockOrder);

        List<OrderDto> result = orderService.getAllOrders();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void deleteOrderSucess() {
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(OrderEntity.builder().build()));

        orderService.deleteOrder("11111");

        Mockito.verify(orderRepository, Mockito.times(1)).deleteById("11111");
    }

    private static OrderDto createOrder() {
        OrderDto orderDto = OrderDto.builder()
                .items(Collections.singletonList(ItemsDto.builder()
                        .id(1l)
                        .idIngredient("feijao1")
                        .amount(1)
                        .value(BigDecimal.TEN)
                        .build()))
                .totalValue(BigDecimal.ONE)
                .build();
        return orderDto;
    }

}