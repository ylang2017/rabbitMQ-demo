package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {
    @RabbitListener(queues = "q_fanout")
    @RabbitHandler
    public void process(String mess){
        System.out.println("我是q_fanout的消费者接收到的消息为 ："+mess);
    }

    @RabbitListener(queues = "q_fanout2")
    @RabbitHandler
    public void process2(String mess){
        System.out.println("我是q_fanout2的消费者接收到的消息为："+mess);
    }
}
