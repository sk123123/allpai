package com.allpai.user.service.impl;

import com.allpai.common.constant.NotifiyType;
import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.*;
import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.user.UserConfigEntity;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.user.vo.UserConfigInVo;
import com.allpai.entity.user.vo.UserSetNotifyInVo;
import com.allpai.user.mapper.UserConfigMapper;
import com.allpai.user.mapper.UserInfoMapper;
import com.allpai.user.service.UserConfigService;
import com.allpai.user.feignConfig.VideoFeignClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 13:04
 * 用户配置
 */
@Service("userConfigService")
public class UserConfigServiceImpl implements UserConfigService{
    @Autowired
    private UserConfigMapper userConfigMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private VideoFeignClient videoFeignClient;


    @Override
    public UserConfigEntity queryUserConfigObject(Long configId) {
        return userConfigMapper.queryObject(configId);
    }

    @Override
    public UserConfigEntity queryObjectByUserId(Long userId) {
        return null;
    }

    @Override
    public UserConfigEntity queryObjectByUserIdShow(Long userId) {
        return null;
    }

    @Override
    public List<UserConfigEntity> queryList(Map<String, Object> map) {
        return userConfigMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userConfigMapper.queryTotal(map);
    }

    @Override
    public void save(UserConfigEntity userConfig) {
        userConfigMapper.save(userConfig);
    }

    @Override
    public void update(UserConfigEntity userConfig) {
        userConfigMapper.update(userConfig);
    }

    @Override
    public void delete(Long configId) {
        userConfigMapper.delete(configId);
    }

    @Override
    public void deleteBatch(Long[] configIds) {
        userConfigMapper.deleteBatch(configIds);
    }

    /**
     * 用户通知
     * @param userSetNotifyInVo
     * @param request
     * @return
     */
    @Override
    public R setNotify(UserSetNotifyInVo userSetNotifyInVo, HttpServletRequest request) {
        boolean isSave = false;
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        UserConfigEntity userConfigEntity = queryObjectByUserId(userId);
        if(userConfigEntity == null){
            userConfigEntity = new UserConfigEntity();
            userConfigEntity.setUserId(userId);
            userConfigEntity.setCreateTime(new Date());
            isSave = true;
        }
        Integer notifyType = userSetNotifyInVo.getNotifyType();
        if(notifyType == null )return R.error(ErrorCode.ParamInvalid.getCode(),"notifyType参数不能空");
        Integer operateType = userSetNotifyInVo.getOperateType();
        if(operateType == null )return R.error(ErrorCode.ParamInvalid.getCode(),"operateType参数不能空");
        if(operateType == 0 || operateType == 1){
            if(notifyType == 0){
                userConfigEntity.setLikeNotify(operateType);
                userConfigEntity.setLookNotify(operateType);
                userConfigEntity.setCommentNotify(operateType);
                userConfigEntity.setChatNotify(operateType);
            }else if(notifyType == 1){
                userConfigEntity.setLikeNotify(operateType);
            }else if(notifyType == 2){
                userConfigEntity.setLookNotify(operateType);
            }else if(notifyType == 3){
                userConfigEntity.setCommentNotify(operateType);
            }else if(notifyType == 4){
                userConfigEntity.setChatNotify(operateType);
            }else{
                return R.error(ErrorCode.ParamInvalid.getCode(),"notifyType参数无效");
            }
        }else{
            return R.error(ErrorCode.ParamInvalid.getCode(),"operateType参数无效");
        }
        if(isSave){
            userConfigMapper.save(userConfigEntity);
        }else{
            userConfigMapper.update(userConfigEntity);
        }
        return R.ok(MsgInfo.成功.toString());
    }

    /**
     * 查找通知类型
     * @param request
     * @return
     */
    @Override
    public R findNotifyInfo(HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        UserConfigEntity userConfigEntity = queryObjectByUserIdShow(userId);
        UserConfigInVo UserConfigInVo = new UserConfigInVo();
        BeanUtils.copyProperties(userConfigEntity, UserConfigInVo);
        return R.ok().put(AppConstant.data.toString(),UserConfigInVo);
    }

    @Override
    public void videoLikeNotifiy(Long videoId, String nickName) {
        VideoInfoEntity videoInfo =
//                restTemplate.getForObject
//                ("http://ap-video-service/video/videoinfo/info/77",VideoInfoEntity.class);
//                videoInfoService.queryObject(videoId);
                videoFeignClient.queryObject(videoId);
        Long userId = videoInfo.getUserId();
        UserConfigEntity usrConfigEntity  = queryObjectByUserId(userId);
        if(isNotify(usrConfigEntity)){
            String registrationId = userInfoMapper.queryJpushId(userId);
            nickName = handleNickName(nickName);
            String videoContent  = handleContent(videoInfo.getContent());
            String content = "点赞通知：@" + nickName + "点赞你的视频" + videoContent + ", 去看看吧~" ;
            Map<String,String> extras = new HashMap<String, String>();
            extras.put("type", "1");
            extras.put("videoId", videoId+"");
            JpushUtils.sendPush(registrationId, content, "",extras);
        }
    }

    @Override
    public void commentLikeNotifiy(Long commentId, String nickName) {

    }

    @Override
    public void lookNotifiy(Long userId, String nickName) {

    }

    @Override
    public void commentNotifiy(Long commentId, String nickName) {

    }

    @Override
    public R wifiNotifiy(HttpServletRequest request) {
        return null;
    }

    @Override
    public R wifiFalgUpdate(Map<String, Integer> wifiType, HttpServletRequest request) {
        return null;
    }

    @Override
    public R findCurrencyInfo(HttpServletRequest request) {
        return null;
    }

    @Override
    public R findPrivacyInfo(HttpServletRequest request) {
        return null;
    }

    @Override
    public void updateReadCommentTime(Long userId) {

    }

    @Override
    public R updateVersionNumber(HttpServletRequest request) {
        return null;
    }
    //检查是否通知
    public boolean isNotify(UserConfigEntity usrConfigEntity){
        if(usrConfigEntity == null || usrConfigEntity.getLikeNotify() == NotifiyType.yes.getCode()){
            return true;
        }else{
            return false;
        }
    }
    public String handleNickName(String nickName){
        if(StringUtils.isBlank(nickName))nickName = "某某某";
        return nickName;
    }
    public String handleContent(String content){
        if(content.length() > 10 ) {
            content = content.substring(0, 10);
            content = content  + "...";
        }
        if(StringUtils.isBlank(content)){
            content = "";
        }else{
            content = "《" + content + "》";
        }
        return content;
    }
}
