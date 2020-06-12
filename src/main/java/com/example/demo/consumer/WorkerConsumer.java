package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_worker")
public class WorkerConsumer {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("workerConsumer : " + hello);
    }
}
