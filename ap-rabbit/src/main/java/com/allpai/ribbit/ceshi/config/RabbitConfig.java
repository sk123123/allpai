package com.allpai.ribbit.ceshi.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/24 0024 0:18
 * ribbitMQ配置文件
 * 设置queue
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue defaultQueue(){
        return new Queue("ceshi");
    }
}
