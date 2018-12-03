package com.hwua.springboot.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @RabbitListener(queues="oracle.news")
    public void receiveMsg(String msg){
        System.out.println("收到消息:"+msg);
    }

}
