package com.allpai.user.service;

import com.allpai.common.utils.R;
import com.allpai.entity.common.vo.SendCodeInVo;
import com.allpai.entity.common.vo.SendCodeInfoInVo;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.user.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 21:50
 */
public interface UserInfoService {
    UserInfoEntity queryObject(Long userId);

    List<UserInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserInfoEntity userInfo);

    void update(UserInfoEntity userInfo);

    void delete(Long userId);

    void deleteBatch(Long[] userIds);

    R regist(UserInfoRegInVo userInfoRegInVo, HttpServletRequest request);

    R sendCode(SendCodeInVo sendCodeVo, HttpServletRequest request);

    R login(UserLoginInVo userLoginInVo, HttpServletRequest request);

    R authLogin(UserAuthLoginInVo userAuthLoginInVo, HttpServletRequest request);

    R findPwd(UserFindPwdInVo userFindPwdInVo, HttpServletRequest request);

    UserInfoEntity getSingUserInfoEntity(String mobile, String qqId, String wechatId, String token, String userNum);

    R modifyPwd(UserModifyPwdInVo userModifyPwdInVo,HttpServletRequest request);

    R sendCodeInfoInVo(SendCodeInfoInVo sendCodeInfoInVo, HttpServletRequest request);

    R updateInfo(UserInfoUpateInVo userInfoUpateInVo, HttpServletRequest request);

    R findUserInfo(UserFindInfoInVo userFindInfoInVo, HttpServletRequest request);

    void handleFanNum(Long userId,Long num);

    void handleLookNum(Long userId,Long num);

    R looklist(UserLookListInVo userLookListInVo,HttpServletRequest request);

    R exit(HttpServletRequest request);

    R blacklist(PageListInVo userBackListInVo ,HttpServletRequest request);

    R deleteBlackListInfo(UserDeleteBlackListInfoInVo userDeleteBlackListInfoInVo, HttpServletRequest request);

}
