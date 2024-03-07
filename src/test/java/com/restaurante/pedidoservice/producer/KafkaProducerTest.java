package com.restaurante.pedidoservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;

public class KafkaProducerTest {

    @InjectMocks
    private Producer producer;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void producerWithPedidoTopicShouldReturnSuccess() throws JsonProcessingException {

        Mockito.when(objectMapper.writeValueAsString(any())).thenReturn("order");

        producer.sendMessage("pedido", "order");

        Mockito.verify(kafkaTemplate, Mockito.times(1)).send(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    }

    @Test
    public void producerWithPedidoTopicShouldReturnError() throws JsonProcessingException {

        Mockito.when(objectMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

        Assertions.assertThrows(JsonProcessingException.class, () -> {
            producer.sendMessage("pedido", "order");
        });
    }
}