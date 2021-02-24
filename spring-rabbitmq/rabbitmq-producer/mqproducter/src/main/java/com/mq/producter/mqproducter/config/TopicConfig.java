package com.mq.producter.mqproducter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机配置w
 */
@Configuration
public class TopicConfig {
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
