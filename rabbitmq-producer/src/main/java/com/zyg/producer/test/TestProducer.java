package com.zyg.producer.test;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@SpringBootTest
public class TestProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 作者：Lun
     * 功能：1. 发送direct（直连消息）：此时的routingkey与监听方的queue名字一样
     * 时间：2021/12/20 22:48
     * 参数：
     * 返回值：
     */
    @Test
    public void test01(){
        rabbitTemplate.convertAndSend("zelin","我爱学习");
    }
    
    /**
     * 作者：Lun
     * 功能：2、发送fanout(分裂消息)：此时只需要对exchange发送消息，与其关联的队列都会收到消息！
     * 时间：2021/12/20 22:59
     * 参数：
     * 返回值：
     */
    @Test
    public void test02(){
        rabbitTemplate.convertAndSend("zelin-school","","fanout消息~~~~");
    }
    /**
     * 作者：Lun
     * 功能：3、发送topic（主题消息）：此时只接受感兴趣的消息【此时发送的是以goods开头的消息】
     * 时间：2021/12/20 23:20
     * 参数：
     * 返回值：
     */
    @Test
    public void test03(){
        rabbitTemplate.convertAndSend("books","goods.#","发送goods开头的消息！");
    }
    
    
    /**
     * 作者：Lun
     * 功能：4、发送topic（主题消息）：此时只接受感兴趣的消息【此时发送的是以logs结尾的消息】
     * 时间：2021/12/20 23:20
     * 参数：
     * 返回值：
     */
    @Test
    public void test04(){
        rabbitTemplate.convertAndSend("books","#.logs","发送logs结尾的消息！");
    }


    /**
     * 作者：Lun
     * 功能：5、发送topic（主题消息）：此时只接受感兴趣的消息【此时发送的是以goods开头并且以logs结尾的消息】
     * 时间：2021/12/20 23:20
     * 参数：
     * 返回值：
     */
    @Test
    public void test05(){
        rabbitTemplate.convertAndSend("books","goods.logs","发送即以goods开头又以logs结尾的消息！");
    }
}
