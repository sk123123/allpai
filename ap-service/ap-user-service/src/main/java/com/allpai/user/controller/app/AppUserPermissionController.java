package com.allpai.user.controller.app;

import com.allpai.entity.common.vo.UserVideoLikeInVo;
import com.allpai.entity.video.vo.VideoCommentAddInVo;
import com.allpai.user.service.UserInfoPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 21:05
 */
@RestController
@RequestMapping("app/user/permiss")
public class AppUserPermissionController {
    @Autowired
    private UserInfoPermissionService userInfoPermissionService;
    //远程调用----用户点赞权限
    @ResponseBody
    @RequestMapping("/like")
    public Map<String,Object> likePermission(@RequestBody UserVideoLikeInVo userVideoLikeInVo, HttpServletRequest request){
        return userInfoPermissionService.likePermission(userVideoLikeInVo,request);
    }

    //远程调用----用户黑名单权限
    @ResponseBody
    @RequestMapping("/blacklist")
    public Map<String,Object> blacklistPermission(@RequestBody VideoCommentAddInVo videoCommentListInVo, HttpServletRequest request){
        return userInfoPermissionService.blacklistPermission(videoCommentListInVo,request);
    }
}
