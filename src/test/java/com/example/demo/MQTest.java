package com.example.demo;

import com.example.demo.productor.FanoutProducer;
import com.example.demo.productor.SimpleProducer;
import com.example.demo.productor.TopicProducer;
import com.example.demo.productor.WorkerProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MQTest {
    @Autowired
    SimpleProducer simpleProducer;
    @Autowired
    WorkerProducer workerProducer;
    @Autowired
    TopicProducer topicProducer;
    @Autowired
    FanoutProducer fanoutProducer;

    @Test
    public void testSimpleModal() throws InterruptedException {
        simpleProducer.send();
        //休眠3s避免app关闭,给消费者响应时间
        Thread.sleep(3000);
    }

    @Test
    public void testWorkerModal() throws InterruptedException {
        for(int i =0;i<5;i++){
            workerProducer.send(i);
            Thread.sleep(500);
        }
        //休眠1s避免app关闭,给消费者响应时间
        Thread.sleep(1000);
    }

    @Test
    public void testTopicModal() throws InterruptedException {
        for(int i =0;i<5;i++){
            //设置的routinekey是topic.test,所以两个队列都可以接到这个消息
            topicProducer.send(i);
            Thread.sleep(500);
        }
        //休眠1s避免app关闭,给消费者响应时间
        Thread.sleep(1000);

        System.out.println("-------------------第二次测试-----------------");

        for(int i =0;i<5;i++){
            //设置的routinekey是topic.afeafeaf.faefaef,所以只有test.#关联的队列都可以接到这个消息
            topicProducer.send1(i);
            Thread.sleep(500);
        }
        //休眠1s避免app关闭,给消费者响应时间
        Thread.sleep(1000);
    }

    @Test
    public void testFanoutModal() throws InterruptedException {
        for(int i =0;i<5;i++){
            //所有绑定了主题交换机的队列都可以接到这个消息
            fanoutProducer.send(i);
            Thread.sleep(500);
        }
        //休眠1s避免app关闭,给消费者响应时间
        Thread.sleep(1000);
    }

}
