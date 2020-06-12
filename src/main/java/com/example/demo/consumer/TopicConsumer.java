package com.example.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumer {
    @RabbitListener(queues = "q_topic_test")
    @RabbitHandler
    public void process(String mess){
        System.out.println("我是q_topic_test的消费者接收到的消息为 ："+mess);
    }
    @RabbitListener(queues = "q_topic_test_queue2")
    @RabbitHandler
    public void process2(String mess){
        System.out.println("我是q_topic_test_queue2的消费者接收到的消息为："+mess);
    }
}
