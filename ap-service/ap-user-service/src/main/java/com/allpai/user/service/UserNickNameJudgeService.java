package com.allpai.user.service;

import com.allpai.entity.user.vo.UserAuthLoginInVo;
import com.allpai.entity.user.vo.UserInfoRegInVo;
import com.allpai.entity.user.vo.UserInfoUpateInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/11 0011 10:47
 * 用户昵称处理
 */
public interface UserNickNameJudgeService {
    //用户注册设置默认昵称
    Map<String,Object> registKeyCodeJudge(UserInfoRegInVo userInfoRegInVo, HttpServletRequest request);

    //三方授权判断是否有敏感词汇
    Map<String, Object> authLogKeyCodeJudge(UserAuthLoginInVo userAuthLoginInVo, HttpServletRequest request);

    //更新用户昵称
    Map<String, Object> updateKeyCodeJudge(UserInfoUpateInVo userInfoUpateInVo, HttpServletRequest request);
}
