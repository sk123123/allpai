package com.allpai.ribbit.ceshi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/24 0024 0:06
 * 客户端
 */
@Component
public class Sender {
    private final static Logger logger = LoggerFactory.getLogger(Sender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(){
        String context = "hello ribbitMQ"+new Date();
        logger.info("客户端发布消息："+ context);
        this.amqpTemplate.convertAndSend("ceshi",context);
    }
}
