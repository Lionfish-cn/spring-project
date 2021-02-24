package com.mq.producter.mqproducter.action;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/mq")
public class ProducerAction {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @RequestMapping("/sending")
    public void sending(){
        try{
            String uuid = String.valueOf(UUID.randomUUID());
            String message = "This is a first message!";
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            Map<String,Object> m = new HashMap<>();
            m.put("messageId", uuid);
            m.put("createTime",date);
            m.put("message",message);
            rabbitTemplate.convertAndSend("amq.direct","directRouting",m);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping("/sendtofanout")
    public void sendToFanout(){
        try{
            String uuid = String.valueOf(UUID.randomUUID());
            String message = "This is a second message to Fanout!";
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            Map<String,Object> m = new HashMap<>();
            m.put("messageId", uuid);
            m.put("createTime",date);
            m.put("message",message);
            rabbitTemplate.convertAndSend("amq.fanout",null,m);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping("/sendtotopic1")
    public void sendToTopicMan(){
        try{
            String uuid = String.valueOf(UUID.randomUUID());
            String message = "This is a first message to topic : Man!";
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            Map<String,Object> m = new HashMap<>();
            m.put("messageId", uuid);
            m.put("createTime",date);
            m.put("message",message);
            rabbitTemplate.convertAndSend("amq.topic","topic.man",m);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/sendtotopic2")
    public void sendToTopicWoman(){
        try{
            String uuid = String.valueOf(UUID.randomUUID());
            String message = "This is a first message to topic : Woman!";
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            Map<String,Object> m = new HashMap<>();
            m.put("messageId", uuid);
            m.put("createTime",date);
            m.put("message",message);
            rabbitTemplate.convertAndSend("amq.topic","topic.asdasd",m);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping("/noExchange")
    public void sendToNoExchange(){
        String uuid = String.valueOf(UUID.randomUUID());
        String message = "This is a first message to topic : Woman!";
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        Map<String,Object> m = new HashMap<>();
        m.put("messageId", uuid);
        m.put("createTime",date);
        m.put("message",message);
        rabbitTemplate.convertAndSend("amq.topic1","topic.asdasd",m);
    }

    @RequestMapping("/noRoute")
    public void sendToNoRoute(){
        String uuid = String.valueOf(UUID.randomUUID());
        String message = "This is a first message to topic : Woman!";
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        Map<String,Object> m = new HashMap<>();
        m.put("messageId", uuid);
        m.put("createTime",date);
        m.put("message",message);
        rabbitTemplate.convertAndSend("amq.topic","asdasdsad",m);
    }


    @RequestMapping("/allNo")
    public void sendToAllNo(){
        String uuid = String.valueOf(UUID.randomUUID());
        String message = "This is a first message to topic : Woman!";
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        Map<String,Object> m = new HashMap<>();
        m.put("messageId", uuid);
        m.put("createTime",date);
        m.put("message",message);
        rabbitTemplate.convertAndSend("amq.topic1","asdasdsad1",m);
    }

}
