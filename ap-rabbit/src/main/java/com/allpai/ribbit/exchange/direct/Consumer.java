package com.allpai.ribbit.exchange.direct;

import com.allpai.ribbit.utils.ConnectionUtil;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/22 0022 15:25
 * 消费者
 */
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final static String EXCHANGE_NAME = "ribbitMQ-exchange";
    private final static String ROUING_KEY = "testRoutingKey";
    private final static String QUEUE_NAME = "queueName";
    public static void main(String[] args) throws Exception {
        //创建代理服务器
        Connection connection = ConnectionUtil.getConnection();
        //创建管道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.DIRECT, true);
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //绑定
//        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, ROUING_KEY);
        logger.info("开始创建consumer");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                //获取消息内容然后处理
                String msg = new String(body, "UTF-8");
                logger.info("路由键:"+envelope.getRoutingKey()+"//"+"获取消息内容："+msg);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
//        channel.close();
//        connection.close();
    }
}