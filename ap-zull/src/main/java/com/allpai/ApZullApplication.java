package com.allpai;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 10:19
 * 网关
 */
@SpringCloudApplication
@EnableZuulProxy
public class ApZullApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApZullApplication.class);
    }
}
