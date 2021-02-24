package com.mq.mqcustomer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDirectConfig {

    @Bean
    Queue directQueue(){
        return new Queue("directQueue",true);
    }

    Binding bindingExchange(){
        return BindingBuilder.bind(directQueue()).to(new DirectExchange("amq.direct")).with("directRouting");
    }

}
