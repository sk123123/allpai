package com.allpai.ribbit.exchange.fanout;

import com.allpai.ribbit.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/23 0023 23:00
 * 扇形交换机 fanout
 */
public class ProduceFanOut {
    private final static String EXCHANGE_NAME_1 = "ribbitMQ-exchange1";
    private final static String EXCHANGE_NAME_2 = "ribbitMQ-exchange2";
    private final static String ROUING_KEY_1 = "testRoutingKey1";
    private final static String ROUING_KEY_2 = "testRoutingKey2";
    private final static String QUEUE_NAME_1 = "queueName1";
    private final static String QUEUE_NAME_2 = "queueName1";
    private final static Logger logger = LoggerFactory.getLogger(ProduceFanOut.class);
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("开始创建信道");
        Channel channel = connection.createChannel();
        logger.info("声明队列");
//        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
//        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        logger.info("声明交换机类型");
        channel.exchangeDeclare(EXCHANGE_NAME_1, ExchangeTypes.FANOUT);
        logger.info("客户端发布消息");
        channel.basicPublish(EXCHANGE_NAME_1, ROUING_KEY_1, null, "这里是客户端，这是由交换机1绑定的队列信息1（fanout）".getBytes());
        channel.basicPublish(EXCHANGE_NAME_1, ROUING_KEY_2, null, "这里是客户端，这是由交换机1绑定的队列信息2（fanout）".getBytes());
        channel.close();
        connection.close();
        logger.info("客户端发布消息成功");
    }
}
