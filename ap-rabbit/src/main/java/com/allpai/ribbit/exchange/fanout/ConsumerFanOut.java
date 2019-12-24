package com.allpai.ribbit.exchange.fanout;

import com.allpai.ribbit.utils.ConnectionUtil;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/23 0023 23:15
 */
public class ConsumerFanOut {
    private final static String EXCHANGE_NAME_1 = "ribbitMQ-exchange1";
    private final static String EXCHANGE_NAME_2 = "ribbitMQ-exchange2";
    private final static String ROUING_KEY_1 = "testRoutingKey1";
    private final static String ROUING_KEY_2 = "testRoutingKey2";
    private final static String QUEUE_NAME_1 = "queueName1";
    private final static String QUEUE_NAME_2 = "queueName1";
    private final static Logger logger = LoggerFactory.getLogger(ConsumerFanOut.class);
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("管道创建");
        Channel channel = connection.createChannel();
        logger.info("队列声明");
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        logger.info("交换机声明");
        channel.exchangeDeclare(EXCHANGE_NAME_1, ExchangeTypes.FANOUT);
        channel.exchangeDeclare(EXCHANGE_NAME_2, ExchangeTypes.FANOUT);
        logger.info("开始绑定");
        channel.queueBind(QUEUE_NAME_1,EXCHANGE_NAME_1,ROUING_KEY_1);
        channel.queueBind(QUEUE_NAME_2,EXCHANGE_NAME_2,ROUING_KEY_2);
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                logger.info("开始获取消息");
                String msg = new String(body, "UTF-8");
                logger.info("路由键:"+envelope.getRoutingKey()+"//"+"获取消息内容："+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME_1, true, consumer);
        channel.basicConsume(QUEUE_NAME_2, true, consumer);
    }
}
