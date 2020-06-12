package com.example.demo.productor;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 一对多或多对多
 */
@Component
public class WorkerProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Integer index) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Sender : " + context+"-"+index);
        //简单队列的情况下routingKey即为Queue名
        this.rabbitTemplate.convertAndSend("q_worker", context+" - "+index);
    }
}
