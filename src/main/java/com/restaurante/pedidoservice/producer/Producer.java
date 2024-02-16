package com.restaurante.pedidoservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public void sendMessage(String topic, Object message) throws JsonProcessingException {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(topic, objectMapper.writeValueAsString(message));
    }

}