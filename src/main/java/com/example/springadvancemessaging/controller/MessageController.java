package com.example.springadvancemessaging.controller;

import com.example.springadvancemessaging.dto.User;
import com.example.springadvancemessaging.service.RabbitMQProducer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MessageController {
  private final RabbitMQProducer rabbitMQProducer;

  @GetMapping("/publish")
  public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
    rabbitMQProducer.sendMessage(message);
    return ResponseEntity.ok("Message sent to Rabbit MQ");
  }

  @PostMapping("/publish")
  public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
    rabbitMQProducer.sendJsonMessage(user);
    return ResponseEntity.ok("Json message sent");
  }
}
