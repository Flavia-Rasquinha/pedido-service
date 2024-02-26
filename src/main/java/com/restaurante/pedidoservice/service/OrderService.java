package com.restaurante.pedidoservice.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.enums.StatusEnum;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.producer.Producer;
import com.restaurante.pedidoservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final Producer producer;
    @Value("${kafka.topic.name}")
    private String kafkaTopicName;

    public OrderDto createOrder(OrderDto order) throws JsonProcessingException {
        logger.info("Starting order creation");

        var orderEntity = objectMapper.convertValue(order, OrderEntity.class);

        AtomicReference<BigDecimal> valueTotal = new AtomicReference<>(BigDecimal.ZERO);
        orderEntity.getItems().forEach(items -> {
            valueTotal.set(valueTotal.get().add(items.value()));
        });
        orderEntity.setTotalValue(valueTotal.get());
        orderEntity.setStatus(StatusEnum.REQUESTED);
        OrderEntity saveOrder = orderRepository.save(orderEntity);

        this.producer.sendMessage(kafkaTopicName, saveOrder);

        logger.info("Order Sent, orderId: {}", saveOrder.getId());
        return objectMapper.convertValue(saveOrder, OrderDto.class);
    }

    public OrderDto updateOrder(String orderId, StatusEnum status) {
        logger.info("Starting order update");
        var order = validateOrderId(orderId);
        OrderEntity updateEntity = OrderEntity.builder()
                .id(orderId)
                .items(order.getItems())
                .totalValue(order.getTotalValue())
                .status(status)
                .build();

        OrderEntity updateOrder = orderRepository.save(updateEntity);

        logger.info("Updated order, orderid: {}, for status: {}", updateOrder.getId(), updateOrder.getStatus());
        return objectMapper.convertValue(updateOrder, OrderDto.class);
    }
    public List<OrderDto> getAllOrders() {
        logger.info("Starting the search for orders");

        List<OrderEntity> products = orderRepository.findAll();
        return products.stream()
                .map(product -> objectMapper.convertValue(product, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(String id) throws OrderNotFoundException {
        logger.info("Starting the order search");

        var order = validateOrderId(id);

        return objectMapper.convertValue(order, OrderDto.class);
    }

    public void deleteOrder(String id) {
        logger.info("Starting order deletion");

        validateOrderId(id);
        orderRepository.deleteById(id);
    }

    private OrderEntity validateOrderId(String id) {
        logger.info("Validating if request id: {} exists", id);

        Optional<OrderEntity> productOptional = orderRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new OrderNotFoundException("Request not found for ID: " + id);
        }
        return productOptional.get();
    }

}
