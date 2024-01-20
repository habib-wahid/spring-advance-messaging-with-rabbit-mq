package com.example.springadvancemessaging.service;

import com.example.springadvancemessaging.dto.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

  private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);
  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  @Value("${rabbitmq.routing.json.key}")
  private String jsonRoutingKey;

  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(String message){
    logger.info(String.format("Message sent -> %s", message));
    rabbitTemplate.convertAndSend(exchange,routingKey,message);
  }

  public void sendJsonMessage(User user){
    logger.info(String.format("Json message sent -> %s", user.toString()));
    rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
  }

}
