package com.restaurante.pedidoservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.producer.Producer;
import com.restaurante.pedidoservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private final ObjectMapper modelMapper;
    private final Producer producer;

    public OrderDto createOrder(String topic, OrderDto order) throws JsonProcessingException {
        var orderEntity = modelMapper.convertValue(order, OrderEntity.class);

        OrderEntity saveOrder = orderRepository.save(orderEntity);

        this.producer.sendMessage(topic, saveOrder);

        return modelMapper.convertValue(saveOrder, OrderDto.class);
    }

    public OrderDto updateOrder(String orderId, String status) {
        var order = validateOrderId(orderId);
        OrderEntity updateOrder = OrderEntity.builder()
                .id(orderId)
                .items(order.getItems())
                .totalValue(order.getTotalValue())
                .status(status)
                .build();

        return modelMapper.convertValue(orderRepository.save(updateOrder), OrderDto.class);
    }
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> products = orderRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.convertValue(product, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(String id) throws OrderNotFoundException {
        var order = validateOrderId(id);

        return modelMapper.convertValue(order, OrderDto.class);
    }

    public void deleteOrder(String id) {
        validateOrderId(id);
        orderRepository.deleteById(id);
    }

    private OrderEntity validateOrderId(String id) {
        Optional<OrderEntity> productOptional = orderRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new OrderNotFoundException("Pedido não encontrado para o ID: " + id);
        }
        return productOptional.get();
    }

}
