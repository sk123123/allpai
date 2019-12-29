package com.allpai.user.controller.app;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/29 0029 15:29
 * 用户反馈
 */

import com.allpai.common.utils.R;
import com.allpai.entity.user.vo.UserFeedbackAddInVo;
import com.allpai.user.service.UserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("app/user/userfeedback")
public class AppUserFeedbackController {
    @Autowired
    private UserFeedbackService userFeedbackService;

    /**
     * 添加用户反馈信息
     */
    @ResponseBody
    @RequestMapping("/add")
    public R add(@RequestBody UserFeedbackAddInVo userFeedbackAddInVo, HttpServletRequest request){
        return userFeedbackService.add(userFeedbackAddInVo, request);
    }
}
