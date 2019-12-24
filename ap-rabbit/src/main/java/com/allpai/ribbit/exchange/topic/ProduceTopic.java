package com.allpai.ribbit.exchange.topic;


import com.allpai.ribbit.utils.ConnectionUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.util.UUID;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/23 0023 23:38
 * topic 主题交换机
 * 用于匹配一个或多个路由键的，可以用*代替
 */
public class ProduceTopic {
    private final static String EXCHANGE_NAME = "topic-exchange";
    private final static String ROUING_KEY_1 = "apple.rk1";
    private final static String ROUING_KEY_2 = "apple.rk2";
    private final static String ROUING_KEY_3 = "apple.rk3";
    private final static String ROUING_KEY_4 = "apple.rk4";
    private final static String ROUING_KEY_5 = "apple.rk5";
    private final static String ROUING_KEY_6 = "apple.rk6";
    private final static String QUEUE_NAME = "queueTopic";
    private final static Logger logger = LoggerFactory.getLogger(ProduceTopic.class);
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.TOPIC);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        AMQP.BasicProperties properties = new AMQP.BasicProperties();
//        properties.messageId(String.valueOF(UUID.randomUUID()));

        //消息幂等性
        AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
        properties.messageId(String.valueOf(UUID.randomUUID()));
        logger.info("客户端开始发布消息");
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY_1,properties.messageId(String.valueOf(UUID.randomUUID())).build(),(ROUING_KEY_1+"路由键-客户端发送").getBytes());
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY_2,null,(ROUING_KEY_2+"路由键-客户端发送").getBytes());
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY_3,null,(ROUING_KEY_3+"路由键-客户端发送").getBytes());
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY_4,null,(ROUING_KEY_4+"路由键-客户端发送").getBytes());
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY_5,null,(ROUING_KEY_5+"路由键-客户端发送").getBytes());
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY_6,null,(ROUING_KEY_6+"路由键-客户端发送").getBytes());
        channel.close();
        connection.close();
        logger.info("客户端发布消息成功");
    }
}
