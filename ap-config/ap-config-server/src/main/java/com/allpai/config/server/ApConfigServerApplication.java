package com.allpai.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/25 0025 18:02
 * config 服务端
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableConfigServer
public class ApConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApConfigServerApplication.class);
    }
}
