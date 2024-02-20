package com.restaurante.pedidoservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.producer.Producer;
import com.restaurante.pedidoservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private OrderRepository orderRepository;
    private final ObjectMapper modelMapper;
    private final Producer producer;

    public OrderDto createOrder(OrderDto order) throws JsonProcessingException {
        logger.info("Iniciando a criação do pedido");

        var orderEntity = modelMapper.convertValue(order, OrderEntity.class);

        AtomicReference<BigDecimal> valueTotal = new AtomicReference<>(BigDecimal.ZERO);
        orderEntity.getItems().forEach(items -> {
            valueTotal.set(valueTotal.get().add(items.value()));
        });
        orderEntity.setTotalValue(valueTotal.get());

        OrderEntity saveOrder = orderRepository.save(orderEntity);

        this.producer.sendMessage("pedidos", saveOrder);

        logger.info("Pedido Enviado, idPedido: {}", saveOrder.getId());
        return modelMapper.convertValue(saveOrder, OrderDto.class);
    }

    public OrderDto updateOrder(String orderId, String status) {
        logger.info("Iniciando a atualização do pedido");
        var order = validateOrderId(orderId);
        OrderEntity updateOrder = OrderEntity.builder()
                .id(orderId)
                .items(order.getItems())
                .totalValue(order.getTotalValue())
                .status(status)
                .build();

        OrderEntity updatePedido = orderRepository.save(updateOrder);

        logger.info("Pedido atualizado, idPedido: {}, para status: {}", updatePedido.getId(), updatePedido.getStatus());
        return modelMapper.convertValue(updatePedido, OrderDto.class);
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
