package com.allpai.ribbit.exchange.topic;

import com.allpai.ribbit.utils.ConnectionUtil;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

import java.io.IOException;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/23 0023 23:48
 */
public class ConsumerTopic {
    private final static String EXCHANGE_NAME = "topic-exchange";
    private final static String QUEUE_NAME = "queueTopic";
    private final static Logger logger = LoggerFactory.getLogger(ConsumerTopic.class);

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, ExchangeTypes.TOPIC);
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"apple.*");
        logger.info("创建consemer对象");
        DefaultConsumer consumer  = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                logger.info("获取客户端消息"+msg);
            }

        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
