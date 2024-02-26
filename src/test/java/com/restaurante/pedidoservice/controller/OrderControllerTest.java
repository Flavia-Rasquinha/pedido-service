package com.restaurante.pedidoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurante.pedidoservice.dto.ItemsDto;
import com.restaurante.pedidoservice.dto.OrderDto;
import com.restaurante.pedidoservice.entity.OrderEntity;
import com.restaurante.pedidoservice.enums.StatusEnum;
import com.restaurante.pedidoservice.exception.OrderNotFoundException;
import com.restaurante.pedidoservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(OrderControllerTest.class)
class OrderControllerTest {
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(orderController).build();
    }

    @Test
    void createOrderSucessTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        OrderDto orderDto = createOrder();
        String payload = objectMapper.writeValueAsString(orderDto);
        mockMvc.perform(
                        post("/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
                                .header("topic", "pedido"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void createOrderErrorTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String payload = objectMapper.writeValueAsString("");

        mockMvc.perform(
                        post("/order")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
                                .header("topic", ""))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void updateOrderSucessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/order/{id}", 123)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(StatusEnum.CANCELED)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void updateOrderErrorTest() throws Exception {

        mockMvc.perform(
                        patch("/order/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("READY"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void getOrderByIdSucessTest() throws Exception {
        mockMvc.perform(
                        get("/order/1111"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void getOrderByIdErrorTest() throws Exception {
        Mockito.when(orderService.getOrderById(any())).thenThrow(OrderNotFoundException.class);

        mockMvc.perform(
                        get("/order/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    void getAllOrdersSuccess() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void deleteOrderSucessTest() throws Exception {

        mockMvc.perform(
                        delete("/order/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void deleteOrderErrorTest() throws Exception {

        mockMvc.perform(
                        delete("/order"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
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