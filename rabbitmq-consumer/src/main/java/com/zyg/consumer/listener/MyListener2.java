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
@RabbitListener(queues = "zelin-sz")
@Component
public class MyListener2 {
    @RabbitHandler
    public void getMessage(String msg){
        System.out.println("sz收到：" + msg);
    }
}
