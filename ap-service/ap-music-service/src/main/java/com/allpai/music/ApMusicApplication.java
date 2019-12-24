package com.allpai.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 10:32
 * 音乐微服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApMusicApplication.class);
    }
}
