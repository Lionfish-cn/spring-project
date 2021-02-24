package com.mq.producter.mqproducter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 扇形交换机配置
 */
@Configuration
public class FanoutConfig {
    @Bean
    Queue firstQueue(){
        return new Queue("firstFanoutQueue",true);
    }

    @Bean
    Queue secondQueue(){
        return new Queue("secondFanoutQueue",true);
    }

    @Bean
    Queue thirdQueue(){
        return new Queue("thirdFanoutQueue",true);
    }

    @Bean
    Binding bindingFirstExchange(){
        return BindingBuilder.bind(firstQueue()).to(new FanoutExchange("amq.fanout"));
    }

    @Bean
    Binding bindingSecondExchange(){
        return BindingBuilder.bind(secondQueue()).to(new FanoutExchange("amq.fanout"));
    }

    @Bean
    Binding bindingThirdExchange(){
        return BindingBuilder.bind(thirdQueue()).to(new FanoutExchange("amq.fanout"));
    }
}
