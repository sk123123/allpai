package com.allpai.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/22 0022 12:06
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
