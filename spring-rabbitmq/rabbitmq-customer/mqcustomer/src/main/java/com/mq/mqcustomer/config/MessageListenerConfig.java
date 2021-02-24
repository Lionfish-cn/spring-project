package com.mq.mqcustomer.config;

import com.mq.mqcustomer.ack.MyAckListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private MyAckListener myAckListener;
    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(cachingConnectionFactory);
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setMaxConcurrentConsumers(1);
        //设置确认消息的模式，将自动确认改为手动确认
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置监听的队列
        listenerContainer.addQueueNames("directQueue","topic.man");
        listenerContainer.setMessageListener(myAckListener);
        return listenerContainer;
    }


}
