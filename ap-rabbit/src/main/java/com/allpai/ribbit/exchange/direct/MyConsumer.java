package com.allpai.ribbit.exchange.direct;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/22 0022 16:11
 */
public class MyConsumer extends DefaultConsumer {
    public MyConsumer(Channel channel) {
        super(channel);
//        handleDelivery(channel);
    }
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("-----------consume message----------");
        System.out.println("consumerTag: " + consumerTag);
        System.out.println("envelope: " + envelope);
        System.out.println("properties: " + properties);
        System.out.println("body: " + new String(body));
    }
}
