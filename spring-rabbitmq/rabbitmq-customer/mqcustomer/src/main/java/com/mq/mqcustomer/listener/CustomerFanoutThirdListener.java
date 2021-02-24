package com.mq.mqcustomer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = {"thirdFanoutQueue"})
public class CustomerFanoutThirdListener {

    @RabbitHandler
    public void process(Map m){
        System.out.println("CustomerFanoutThirdListener接受到的消息为："+m.toString());
    }
}
