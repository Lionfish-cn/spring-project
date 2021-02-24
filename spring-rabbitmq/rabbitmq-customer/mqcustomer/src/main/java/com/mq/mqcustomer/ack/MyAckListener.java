package com.mq.mqcustomer.ack;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyAckListener implements ChannelAwareMessageListener {

    enum Action{
        ack,resend
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long key = message.getMessageProperties().getDeliveryTag();
        Enum s = null;
        try{
            String msg = message.toString();
            Map<String,Object> m = stringToMap(msg.split("'")[1]);
            Object messageId = m.get("messageId");
            Object createTime = m.get("createTime");
            Object messageData = m.get("message");
            String queue = message.getMessageProperties().getConsumerQueue();
            if(queue.equals("directQueue")){
                System.out.println("接收到队列："+queue+"的消息！");
                System.out.println("接收到的消息为："+m.toString());
                System.out.println("接收到的消息MessageId:"+messageId+",消息主体为："+messageData+"，接收到的时间为："+createTime);
            }

            if(queue.equals("topic.man")){
                System.out.println("接收到队列："+queue+"的消息！");
                System.out.println("接收到的消息为："+m.toString());
                System.out.println("接收到的消息MessageId:"+messageId+",消息主体为："+messageData+"，接收到的时间为："+createTime);
            }

            s = Action.ack;
        }catch(Exception e){
            e.printStackTrace();
            s = Action.resend;
        }finally{
            if(s == Action.ack){//消息处理正常，就正常消费消息
                channel.basicAck(key,true);
            }else if(s == Action.resend){//若有处理异常，则拒绝消费，并将该消息再放入到队列中
                channel.basicReject(key,true);
            }else{//若出现不明情况，则拒绝消费，并且删除队列中的消息
                channel.basicNack(key,false,false);
            }
        }

    }


    public Map<String,Object> stringToMap(String str){
        str = str.substring(1,str.length()-1);
        String[] messages =  str.split(",");
        Map<String,Object> m = new HashMap<>();
        for (String message : messages) {
            m.put(message.split("=")[0].trim(),message.split("=")[1]);
        }
        return m;
    }
}
