package com.allpai.user.controller.app;

import com.allpai.common.utils.R;
import com.allpai.entity.vo.UserSetNotifyInVo;
import com.allpai.user.service.UserConfigService;
import com.allpai.user.service.impl.UserConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 16:13
 */
@RestController
@RequestMapping("app/user/config")
public class AppUserConfigController {
    @Autowired
    private UserConfigService userConfigService;
    @Autowired
    private UserConfigServiceImpl userConfigServiceImpl;

    /**
     * 设置通知
     */
    @ResponseBody
    @RequestMapping("/setNotify")
    public R setNotify(@RequestBody UserSetNotifyInVo userSetNotifyInVo, HttpServletRequest request){
        return userConfigService.setNotify(userSetNotifyInVo, request);
    }

    /**
     * 查找通知配置
     */
    @ResponseBody
    @RequestMapping("/findNotifyInfo")
    public R  findNotifyInfo(HttpServletRequest request){
        return userConfigService.findNotifyInfo(request);
    }

    /**
     * wifi信息通知
     */
    @ResponseBody
    @RequestMapping("/wifiNotifiy")
    public R wifiNotifiy(HttpServletRequest request){
        return userConfigService.wifiNotifiy(request);
    }

    /**
     * wifi状态更新
     */
    @ResponseBody
    @RequestMapping("/wifiFalgUpdate")
    public R  wifiFalgUpdate(@RequestBody Map<String,Integer> wifiType, HttpServletRequest request){
        return userConfigService.wifiFalgUpdate(wifiType, request);
    }

    /**
     * 查找通用配置
     */
    @ResponseBody
    @RequestMapping("/findCurrencyInfo")
    public R  findCurrencyInfo(HttpServletRequest request){
        return userConfigService.findCurrencyInfo(request);
    }

    /**
     * 查找隐私配置
     */
    @ResponseBody
    @RequestMapping("/findPrivacyInfo")
    public R  findPrivacyInfo(HttpServletRequest request){
        return userConfigService.findPrivacyInfo(request);
    }
    @ResponseBody
    @RequestMapping(value = "/videoLikeNotifiy/{videoId}")
    public void  videoLikeNotifiy(@PathVariable Long videoId){
//        userConfigServiceImpl.videoLikeNotifiy(videoId,"java");
        userConfigService.videoLikeNotifiy(videoId,"java");
    }
}
