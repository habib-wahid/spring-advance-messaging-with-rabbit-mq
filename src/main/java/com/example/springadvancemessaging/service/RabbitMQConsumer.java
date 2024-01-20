package com.example.springadvancemessaging.service;

import com.example.springadvancemessaging.dto.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumer {

  private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void consume(String message){
    logger.info(String.format("Received message -> %s", message));
  }

  @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
  public void jsonConsume(User user){
    logger.info(String.format("Received json message -> %s", user.toString()));
  }
}
