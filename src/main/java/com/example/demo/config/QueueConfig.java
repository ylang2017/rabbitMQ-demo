package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    /**
     * 简单模式
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue("q_hello");
    }

    /**
     * 工作者模式
     * @return
     */
    @Bean
    public Queue queueWorker() {
        return new Queue("q_worker");
    }



    /**
     * 主题/路由模式
     * 声明2个topic要使用的queue
     * @return
     */
    @Bean
    public Queue queueTopic(){
        return new Queue("q_topic_test");
    }
    @Bean
    public Queue queueTopic2(){
        return new Queue("q_topic_test_queue2");
    }
    /**
     * 声明一个Topic类型的交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("myExchange");
    }

    /**
     * 绑定topic要使用的queue和交换机（注意这里的参数名称和上面声明的queue和交换机的方法名）
     * @param queueTopic
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueTopic, TopicExchange exchange) {
        //使用固定routinekey的称为路由模式(routine)，使用通配符的称为主题模式（topics）
        return BindingBuilder.bind(queueTopic).to(exchange).with("topic.test");
    }
    @Bean
    Binding bindingExchangeMessages(Queue queueTopic2, TopicExchange exchange) {
        //.#匹配任意个字符，.*匹配任意一个单词
        return BindingBuilder.bind(queueTopic2).to(exchange).with("topic.#");
    }


    /**
     * 发布/订阅模式
     * 声明2个Fanout Exchange要使用的queue
     * @return
     */
    @Bean
    public Queue queueFanout(){
        return new Queue("q_fanout");
    }
    @Bean
    public Queue queueFanout2(){
        return new Queue("q_fanout2");
    }

    /**
     * 声明一个订阅模式交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("myFanoutExchange");
    }

    /**
     * 绑定订阅交换机和队列
     * @param queueFanout
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeA(Queue queueFanout, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue queueFanout2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout2).to(fanoutExchange);
    }

}
