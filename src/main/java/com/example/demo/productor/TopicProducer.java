package com.example.demo.productor;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class TopicProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Integer index) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context+"-"+index);

        //交换机名称，routineKey,消息体
        rabbitTemplate.convertAndSend("myExchange","topic.test",context+index);
    }

    public void send1(Integer index) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context+"-"+index);

        //交换机名称，routineKey,消息体
        rabbitTemplate.convertAndSend("myExchange","topic.afeafeaf.faefaef",context+index);
    }
}
