package com.allpai.user.feignConfig;

import com.allpai.entity.VideoInfoEntity;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/17 0017 15:42
 */
@FeignClient(value = "ap-video-service")
public interface VideoFeignClient {
//    @RequestLine("GET/video/videoinfo/info/{videoId}")
//    VideoInfoEntity queryObject(@Param(value = "videoId") Long videoId);
    @RequestMapping(value = "/video/videoinfo/info/{videoId}",method = RequestMethod.GET)
    VideoInfoEntity queryObject(@PathVariable(value = "videoId") Long videoId);
}
