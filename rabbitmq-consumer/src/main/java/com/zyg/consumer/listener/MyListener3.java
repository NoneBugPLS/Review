package com.zyg.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@RabbitListener(queues = "zelin-gz")
@Component
public class MyListener3 {
    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("gz收到：" + msg);
    }
}
