package com.restaurante.pedidoservice.controller;

import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto order) {
        var productSave = orderService.createOrder(order);
        return new ResponseEntity<>(productSave, HttpStatus.CREATED);
    }

     @GetMapping
    public ResponseEntity<List<OrderDto>> findALlOrders() {
        var products = orderService.getAllOrders();

            return ResponseEntity.ok(products);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findOrdertById(@PathVariable @NotNull Long orderId) throws OrderNotFoundException {
        var orders = orderService.getOrderById(orderId);

        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable @NotNull Long id) throws OrderNotFoundException {
       orderService.deleteOrder(id);

        return ResponseEntity.noContent().build();
    }

}
