package com.allpai.ribbit.ceshi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/24 0024 0:17
 * 消费者
 */
@Component
@RabbitListener(queues = "ceshi")
public class Receiver {
    private final static Logger logger = LoggerFactory.getLogger(Receiver.class);
    @RabbitHandler
    public void process(String hello){
        logger.info("接收客户端信息:" + hello);
    }
}
