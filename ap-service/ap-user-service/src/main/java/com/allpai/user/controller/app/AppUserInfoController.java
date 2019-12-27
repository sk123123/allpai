package com.allpai.user.controller.app;

import com.allpai.common.utils.R;
import com.allpai.entity.common.vo.SendCodeInfoInVo;
import com.allpai.entity.user.vo.*;
import com.allpai.entity.vo.*;
import com.allpai.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 14:33
 * 用户信息表
 */
public class AppUserInfoController {
    @Autowired
    private UserInfoService userInfoService;
//    @Autowired
//    private UserInfoPermissionService userInfoPermissionService;

    /**
     * 修改密码
     */
    @ResponseBody
    @RequestMapping("/modifyPwd")
    public R modifyPwd(@RequestBody UserModifyPwdInVo userInfoRegInVo, HttpServletRequest request){
        return userInfoService.modifyPwd(userInfoRegInVo,request);
    }

    /**
     * 发送登录后验证码信息
     */
    @ResponseBody
    @RequestMapping("/sendCodeInfo")
    public R sendCodeInfo(@RequestBody SendCodeInfoInVo sendCodeInfoInVo, HttpServletRequest request){
        return userInfoService.sendCodeInfoInVo(sendCodeInfoInVo, request);
    }

    /**
     * 更新用户信息
     */
    @ResponseBody
    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody UserInfoUpateInVo userInfoUpateInVo, HttpServletRequest request){
        return userInfoService.updateInfo(userInfoUpateInVo,request);
    }

    /**
     * 查询个人信息 已改变路径 只有1.1版本在使用
     */
    @ResponseBody
    @RequestMapping("/findUserInfo")
    public R findUserInfo(@RequestBody UserFindInfoInVo userFindInfoInVo, HttpServletRequest request){
        return userInfoService.findUserInfo(userFindInfoInVo,request);
    }



    /**
     * 用户关注列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/looklist")
    public R looklist(@RequestBody UserLookListInVo userLookListInVo, HttpServletRequest request){
        return userInfoService.looklist(userLookListInVo, request);
    }

    /**
     * 用户退出应用
     * @return
     */
    @ResponseBody
    @RequestMapping("/exit")
    public R exit(HttpServletRequest request){
        return userInfoService.exit(request);
    }

    /**
     * 黑名单列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/blacklist")
    public R blacklist(@RequestBody PageListInVo userBackListInVo , HttpServletRequest request){
        return userInfoService.blacklist(userBackListInVo, request);
    }

    /**
     * 用户评论控制
     * @return
     */
//    @ResponseBody
//    @RequestMapping("/commentControl")
//    public R commentControl(@RequestBody UserCommentControlInVo userCommentControlInVo ,HttpServletRequest request ){
//        return userInfoPermissionService.commentControlPermission(userCommentControlInVo, request);
//    }

    /**
     * 删除黑名单用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteBlackListInfo")
    public R deleteBlackListInfo(@RequestBody UserDeleteBlackListInfoInVo userDeleteBlackListInfoInVo, HttpServletRequest request){
        return userInfoService.deleteBlackListInfo(userDeleteBlackListInfoInVo, request);
    }
}
