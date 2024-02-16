package com.restaurante.pedidoservice.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private MockMvc mockMvc;

    @Test
    void handleRuntimeException() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(globalExceptionHandler).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/order/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
