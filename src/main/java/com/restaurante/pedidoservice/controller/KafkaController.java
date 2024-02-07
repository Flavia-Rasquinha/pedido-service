package com.restaurante.pedidoservice.controller;

import com.restaurante.pedidoservice.producer.Producer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
@RequiredArgsConstructor
public class KafkaController {

   private final Producer producer;


   @PostMapping(value = "/publish")
   public void sendMessageToKafkaTopic(@RequestParam("message") String message) {

       this.producer.sendMessage(message);
   }
}