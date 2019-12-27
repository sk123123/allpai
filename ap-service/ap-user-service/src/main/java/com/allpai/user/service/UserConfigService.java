package com.allpai.user.service;

import com.allpai.common.utils.R;
import com.allpai.entity.user.UserConfigEntity;
import com.allpai.entity.user.vo.UserSetNotifyInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 13:01
 */

public interface UserConfigService {

    UserConfigEntity queryUserConfigObject(Long configId);

    UserConfigEntity queryObjectByUserId(Long userId);

    UserConfigEntity queryObjectByUserIdShow(Long userId);

    List<UserConfigEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserConfigEntity userConfig);

    void update(UserConfigEntity userConfig);

    void delete(Long configId);

    void deleteBatch(Long[] configIds);

    R setNotify(UserSetNotifyInVo userSetNotifyInVo, HttpServletRequest request);

    R  findNotifyInfo(HttpServletRequest request);

    void videoLikeNotifiy(Long videoId,String nickName);

    void commentLikeNotifiy(Long commentId,String nickName);

    void lookNotifiy(Long userId,String nickName);

    void commentNotifiy(Long commentId,String nickName);

    R wifiNotifiy(HttpServletRequest request);

    R wifiFalgUpdate(Map<String,Integer> wifiType,HttpServletRequest request);

    //查找通用配置
    R findCurrencyInfo(HttpServletRequest request);

    R findPrivacyInfo(HttpServletRequest request);

    void updateReadCommentTime(Long userId);

    R updateVersionNumber( HttpServletRequest request);
}
