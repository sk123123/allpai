package com.allpai.ribbit.exchange.direct;

import com.allpai.ribbit.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/22 0022 15:01
 * 生产者  直连型交换机
 */
public class Produce {
    private final static String EXCHANGE_NAME = "ribbitMQ-exchange";
    private final static String ROUING_KEY = "testRoutingKey";
    private final static String QUEUE_NAME = "queueName";
    private final static Logger logger = LoggerFactory.getLogger(Produce.class);
    public static void main(String[] args) throws Exception {
        //创建代理服务器
        Connection connection = ConnectionUtil.getConnection();
        //创建管道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.DIRECT,true);
        //发布消息
        String message = "hello rabbitMQ";
        logger.info("生产者开始发布消息");
        channel.basicPublish(EXCHANGE_NAME,ROUING_KEY, null,message.getBytes());
        logger.info("生产者发布消息message："+message);
        channel.close();
        connection.close();
        logger.info("生产者发布消息成功");
    }
}
