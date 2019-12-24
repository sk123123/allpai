package com.allpai.user.controller.app;

import com.allpai.common.utils.R;
import com.allpai.entity.vo.*;
import com.allpai.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 14:35
 * 用户登录之前的接口
 */
@RestController
@RequestMapping("app/user/userlogin")
public class AppUserLoginController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 发送验证码
     */
    @ResponseBody
    @RequestMapping("/sendCode")
    public R sendCode(@RequestBody SendCodeInVo sendCodeVo, HttpServletRequest request){
        return userInfoService.sendCode(sendCodeVo, request);
    }

    /**
     * 注册
     */
    @ResponseBody
    @RequestMapping("/regist")
    public R regist(@RequestBody UserInfoRegInVo userInfoRegInVo, HttpServletRequest request){
        return userInfoService.regist(userInfoRegInVo,request);
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping("/login")
    public R login(@RequestBody UserLoginInVo userLoginInVo, HttpServletRequest request){
        return userInfoService.login(userLoginInVo,request);
    }

    /**
     *授权 登录
     */
    @ResponseBody
    @RequestMapping("/authLogin")
    public R authLogin(@RequestBody UserAuthLoginInVo userAuthLoginInVo, HttpServletRequest request){
        return userInfoService.authLogin(userAuthLoginInVo,request);
    }

    /**
     *找回密码
     */
    @ResponseBody
    @RequestMapping("/findPwd")
    public R findPwd(@RequestBody UserFindPwdInVo userInfoRegInVo, HttpServletRequest request){
        return userInfoService.findPwd(userInfoRegInVo, request);
    }
}
