package com.allpai.user.feignConfig;

import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.vo.VideoHotnumInVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/17 0017 15:42
 * 远程调用视频微服务方法
 */
@FeignClient(value = "ap-video-service")
public interface VideoFeignClient {
//    @RequestLine("GET/video/videoinfo/info/{videoId}")
//    VideoInfoEntity queryObject(@Param(value = "videoId") Long videoId);
    //查询视频列表
    @RequestMapping(value = "/video/videoinfo/info/{videoId}",method = RequestMethod.GET)
    VideoInfoEntity queryObject(@PathVariable(value = "videoId") Long videoId);

    //视频点赞数
    @RequestMapping(value = "/video/videoinfo/getGoogNumTotal/{userId}",method = RequestMethod.GET)
    Long  getGoogNumTotal(@PathVariable(value = "userId") Long userId);

    //评论更新数
    @RequestMapping(value = "/app/video/comment/findCommentNoReadNum/{userId}",method = RequestMethod.GET)
    int findCommentNoReadNum(@PathVariable(value = "userId")Long userId);

    //根据type更新热度值
    @RequestMapping(value = "/video/videoinfo/updateVideoHotnum",method = RequestMethod.GET)
    void updateVideoHotnum(@RequestBody VideoHotnumInVo videoHotnumInVo);
}
