package com.allpai.user.controller.app;

import com.allpai.common.utils.R;
import com.allpai.entity.user.vo.UserAddBlackInVo;
import com.allpai.entity.user.vo.UserAttentInVo;
import com.allpai.user.service.UserUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/27 0027 18:10
 * 用户和用户关系表
 */
@RestController
@RequestMapping("app/relation/useruser")
public class AppUserUserRelationController {
    @Autowired
    private UserUserRelationService userUserRelationService;


    /**
     * 关注其他用户
     */
    @ResponseBody
    @RequestMapping("/attentUser")
    public R attentUser(@RequestBody UserAttentInVo userAttentInVo, HttpServletRequest request){
        return userUserRelationService.attentUser(userAttentInVo, request);
    }

    /**
     * 拉黑用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBlack")
    public R addBlack(@RequestBody UserAddBlackInVo userAddBlackInVo, HttpServletRequest request){
        return userUserRelationService.addBlack(userAddBlackInVo, request);
    }
}
