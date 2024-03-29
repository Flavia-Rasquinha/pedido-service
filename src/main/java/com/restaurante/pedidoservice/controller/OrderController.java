package com.restaurante.pedidoservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.enums.StatusEnum;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController  {

    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto order) throws JsonProcessingException {
        var productSave = orderService.createOrder(order);
        return new ResponseEntity<>(productSave, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<OrderDto> updateOrder(@PathVariable @NotNull String id, @RequestBody @Valid StatusEnum status) throws OrderNotFoundException  {
        var productSave = orderService.updateOrder(id, status);
        return new ResponseEntity<>(productSave, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAllOrders() {
        var products = orderService.getAllOrders();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable @NotNull String id) throws OrderNotFoundException {
        var orders = orderService.getOrderById(id);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable @NotNull String id) throws OrderNotFoundException {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
