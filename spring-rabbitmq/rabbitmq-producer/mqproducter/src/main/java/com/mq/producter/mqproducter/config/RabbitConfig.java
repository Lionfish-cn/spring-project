package com.mq.producter.mqproducter.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        //设置强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            System.out.println("相关数据："+correlationData);
            System.out.println("确认情况："+b);
            System.out.println("原因："+s);
        });

        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            System.out.println("交换机为："+returnedMessage.getExchange());
            System.out.println("返回消息为："+returnedMessage.getMessage());
            System.out.println("路由键为："+returnedMessage.getRoutingKey());
            System.out.println("回应消息为："+returnedMessage.getReplyText());
            System.out.println("回应代码为："+returnedMessage.getReplyCode());
        });
        return rabbitTemplate;
    }
}
