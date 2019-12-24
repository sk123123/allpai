package com.allpai.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 11:51
 * 视频模块微服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApVideoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApVideoApplication.class);
    }
}
