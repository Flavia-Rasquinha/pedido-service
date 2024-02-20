package com.restaurante.pedidoservice.controller.documentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Gestão de Pedidos", description = "Endpoints para gerenciar pedidos")
public interface SwaggerOrderSpec {

    @ApiOperation(value = "Criar um pedido",
            notes = "Cria um novo pedido com os detalhes fornecidos.",
            response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Pedido criado com sucesso", response = OrderDto.class),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 500, message = "Erro Interno do Servidor")
    })

    @PostMapping("/order")
    @Transactional
    ResponseEntity<OrderDto> createOrder(
            @RequestBody @Valid OrderDto pedido
    ) throws JsonProcessingException;

    @ApiOperation(value = "Atualizar um pedido",
            notes = "Atualiza o status de um pedido com o novo status fornecido.",
            response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido atualizado com sucesso", response = OrderDto.class),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Erro Interno do Servidor")
    })
    @PatchMapping("/order/{id}")
    @Transactional
    ResponseEntity<OrderDto> updateOrder(
            @PathVariable @NotNull String id,
            @RequestBody @Valid String status
    );

    @ApiOperation(value = "Buscar todos os pedidos",
            notes = "Recupera todos os pedidos cadastrados.",
            response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de pedidos recuperada com sucesso", response = OrderDto.class),
            @ApiResponse(code = 500, message = "Erro Interno do Servidor")
    })
    @GetMapping("/order")
    ResponseEntity<List<OrderDto>> findALlOrders();

    @ApiOperation(value = "Buscar um pedido por ID",
            notes = "Recupera os detalhes de um pedido com base no ID fornecido.",
            response = OrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido recuperado com sucesso", response = OrderDto.class),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Erro Interno do Servidor")
    })
    @GetMapping("/order/{id}")
    ResponseEntity<OrderDto> findOrdertById(@PathVariable @NotNull String id) throws OrderNotFoundException;

    @ApiOperation(value = "Excluir um pedido por ID",
            notes = "Exclui um pedido com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Pedido excluído com sucesso"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 404, message = "Pedido não encontrado"),
            @ApiResponse(code = 500, message = "Erro Interno do Servidor")
    })
    @DeleteMapping("/order/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable @NotNull String id) throws OrderNotFoundException;
}


