package com.allpai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sunkai
 * @version 1.0
 * 服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class ApRegistryApplition {
    public static void main(String[] args) {
        SpringApplication.run(ApRegistryApplition.class);
    }
}
