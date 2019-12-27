package com.allpai.video.feignClient;

import com.allpai.entity.common.vo.UserVideoLikeInVo;
import com.allpai.entity.video.vo.VideoCommentAddInVo;
import com.allpai.entity.video.vo.VideoLikeNotifiyInVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 20:49
 */
@FeignClient("ap-user-service")
public interface UserFeignClient {
    //视频点赞通知
    @RequestMapping(value = "app/user/config/videoLikeNotifiy",method = RequestMethod.GET)
    void videoLikeNotifiy(@RequestBody VideoLikeNotifiyInVo videoLikeNotifiyInVo);

    //视频评论通知
    @RequestMapping(value = "app/user/config/commentNotifiy",method = RequestMethod.GET)
    void commentNotifiy(@RequestParam("commentId") Long commentId, @RequestParam("nickName") String nickName);

    //用户权限点赞控制
    @RequestMapping(value = "app/user/permiss/like",method = RequestMethod.GET)
    Map<String,Object> likePermission(@RequestBody UserVideoLikeInVo userVideoLikeInVo, @RequestParam("request") HttpServletRequest request);

    //用户黑名单权限
    @RequestMapping(value = "app/user/permiss/blacklist",method = RequestMethod.GET)
    Map<String,Object> blacklistPermission(@RequestBody VideoCommentAddInVo videoCommentListInVo,@RequestParam("request") HttpServletRequest request);
}
