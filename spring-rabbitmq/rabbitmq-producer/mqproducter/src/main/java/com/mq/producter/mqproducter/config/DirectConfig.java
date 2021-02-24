package com.mq.producter.mqproducter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 直连交换机配置
 */
public class DirectConfig {
    @Bean
    Queue directQueue(){
        return new Queue("directQueue",true);
    }

    @Bean
    Binding bindingExchange(){
        return BindingBuilder.bind(directQueue()).to(new TopicExchange("amq.direct")).with("directRouting");
    }

}
