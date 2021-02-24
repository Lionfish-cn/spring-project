package com.mq.mqcustomer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerTopicConfig {
    @Bean
    Queue topicQueue(){
        return new Queue("topic.man",true);
    }

    @Bean
    Queue topicQueue1(){
        return new Queue("topic.woman",true);
    }
    @Bean
    Binding bindingTopicExchange2(){
        return BindingBuilder.bind(topicQueue()).to(new TopicExchange("amq.topic")).with("topic.man");
    }
    @Bean
    Binding bindingTopicExchange1(){
        return BindingBuilder.bind(topicQueue1()).to(new TopicExchange("amq.topic")).with("topic.#  ");
    }

}

