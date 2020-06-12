package com.example.demo.productor;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class FanoutProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Integer index) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context+"-"+index);
        //向订阅交换机发送消息，由交换机将消息投放到对应队列
        this.rabbitTemplate.convertAndSend("myFanoutExchange",null, context+"-"+index);
    }
}
