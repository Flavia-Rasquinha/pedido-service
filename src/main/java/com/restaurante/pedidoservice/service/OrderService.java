package com.restaurante.pedidoservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
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

    public OrderDto createOrder(OrderDto order){
        var orderEntity = modelMapper.convertValue(order, OrderEntity.class);

        var orderSave = orderRepository.save(orderEntity);
        return modelMapper.convertValue(orderSave, OrderDto.class);
    }

    public List<OrderDto> getAllOrders() {
        List<OrderEntity> products = orderRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.convertValue(product, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) throws OrderNotFoundException {
        var order = validateOrderId(id);

        return modelMapper.convertValue(order, OrderDto.class);
    }

    public void deleteOrder(Long id) {
        validateOrderId(id);
        orderRepository.deleteById(id);
    }

    private OrderEntity validateOrderId(Long id) {
        Optional<OrderEntity> productOptional = orderRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new OrderNotFoundException("Pedido não encontrado para o ID: " + id);
        }
        return productOptional.get();
    }

}
