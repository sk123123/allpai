package com.allpai.ribbit.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/23 0023 20:09
 * 工厂工具类
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setVirtualHost("/");
        //创建代理服务器
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
