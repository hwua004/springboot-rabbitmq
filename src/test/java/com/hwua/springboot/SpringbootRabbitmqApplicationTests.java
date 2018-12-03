package com.hwua.springboot;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void testdirect() {
        JSONObject obj = new JSONObject();
        obj.put("title","重磅消息，首位艾滋病编码基因人类即将诞生");
        obj.put("time","2018-11-30 12:12:12");
        obj.put("author","张三");
        rabbitTemplate.convertAndSend("exchange.direct","oracle.news",obj.toJSONString());
    }

    @Test
    public void testfanout() {
        JSONObject obj = new JSONObject();
        obj.put("title","重磅消息，首位艾滋病编码基因人类即将诞生");
        obj.put("time","2018-11-30 12:12:12");
        obj.put("author","张三");
        rabbitTemplate.convertAndSend("exchange.fanout","oracle.news",obj.toJSONString());
    }

    @Test
    public void testtopic() {
        JSONObject obj = new JSONObject();
        obj.put("title","重磅消息，首位艾滋病编码基因人类即将诞生");
        obj.put("time","2018-11-30 12:12:12");
        obj.put("author","张三");
        rabbitTemplate.convertAndSend("exchange.topic","oaec.news",obj.toJSONString());
    }
    @Test
    public void testReceive(){
        Object object=rabbitTemplate.receiveAndConvert("oracle.news");
        System.out.println(object);
    }
    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("exchange.amqpadmin"));
    }
    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("oaec.emps"));
    }

    @Test
    public void createBinding(){
        amqpAdmin.declareBinding(new Binding("oaec.emps",
                Binding.DestinationType.QUEUE,"exchange.amqpadmin","oaec.emps",null));
    }

    @Test
    public void testdirect2() {
        JSONObject obj = new JSONObject();
        obj.put("title","重磅消息，首位艾滋病编码基因人类即将诞生");
        obj.put("time","2018-11-30 12:12:12");
        obj.put("author","张三");
        rabbitTemplate.convertAndSend("exchange.amqpadmin","oaec.emps",obj.toJSONString());
    }


}
