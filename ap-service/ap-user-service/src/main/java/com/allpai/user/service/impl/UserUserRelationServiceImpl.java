package com.allpai.user.service.impl;

import com.allpai.common.constant.HotNumType;
import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.MsgInfo;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.user.UserUserRelationEntity;
import com.allpai.entity.user.vo.UserAddBlackInVo;
import com.allpai.entity.user.vo.UserAttentInVo;
import com.allpai.entity.video.vo.VideoHotnumInVo;
import com.allpai.user.feignConfig.VideoFeignClient;
import com.allpai.user.mapper.UserUserRelationMapper;
import com.allpai.user.service.UserConfigService;
import com.allpai.user.service.UserInfoService;
import com.allpai.user.service.UserUserRelationService;
import org.apache.commons.lang.StringUtils;
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
 * @date 2019/12/26 0026 16:47
 * 用户与用户关系表
 */
@Service("userUserRelationService")
public class UserUserRelationServiceImpl implements UserUserRelationService {
    @Autowired
    private UserUserRelationMapper userUserRelationMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private VideoFeignClient videoFeignClient;
    @Autowired
    private UserConfigService userConfigService;

    @Override
    public UserUserRelationEntity queryObject(Long userUserId) {
        return userUserRelationMapper.queryObject(userUserId);
    }

    @Override
    public List<UserUserRelationEntity> queryList(Map<String, Object> map) {
        return userUserRelationMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userUserRelationMapper.queryTotal(map);
    }

    @Override
    public void save(UserUserRelationEntity userUserRelation) {
        userUserRelationMapper.save(userUserRelation);
    }

    @Override
    public void update(UserUserRelationEntity userUserRelation) {
        userUserRelationMapper.update(userUserRelation);
    }

    @Override
    public void delete(Long userUserId) {
        userUserRelationMapper.delete(userUserId);
    }

    @Override
    public void deleteBatch(Long[] userUserIds) {
        userUserRelationMapper.deleteBatch(userUserIds);
    }

    /**
     * 查看用户与用户关系
     * @param userId
     * @param toUserId
     * @param type
     * @return
     */
    @Override
    public boolean checkRelation(Long userId, Long toUserId, Integer type) {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", 0);
        map.put("limit", 1);
        map.put("userId", userId);
        map.put("toUserId", toUserId);
        map.put("type", type);
        List<UserUserRelationEntity> list = userUserRelationMapper.queryList(map);
        if(list != null  && list.size() >0)return true;
        return false;
    }

    /**
     * 关注用户信息
     * @param userAttentInVo
     * @param request
     * @return
     */
    @Override
    public R attentUser(UserAttentInVo userAttentInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        if(userInfoEntity == null || userInfoEntity.getUserId() == null)
            return R.error(ErrorCode.TokenInvalid);
        Long userId = userInfoEntity.getUserId();
        Long toUserId = userAttentInVo.getUserId();
        if(toUserId == 0)return R.error(ErrorCode.ParamInvalid.getCode(),"userId参数不能为0");
        if(toUserId == null )return R.error(ErrorCode.ParamInvalid.getCode(),"userId参数不能空");
        if(userId == toUserId)return R.error(ErrorCode.InfoError.getCode(),"你不能关注自己哦~");
        if(userAttentInVo.getType() == 1){//添加关注
            if(!checkRelation(userId, toUserId,1)){
                deleteRelation(userId, toUserId);
                UserUserRelationEntity userRelationEntity = new UserUserRelationEntity();
                userRelationEntity.setUserId(userId);
                userRelationEntity.setToUserId(toUserId);
                userRelationEntity.setCreateTime(new Date());
                userRelationEntity.setType(1);
                save(userRelationEntity);
                userInfoService.handleFanNum(toUserId, 1L);
                userInfoService.handleLookNum(userId, 1L);
                //关注通知
                userConfigService.lookNotifiy(toUserId, userInfoEntity.getNickName());
                //更新热度值
                VideoHotnumInVo videoHotnumInVo = new VideoHotnumInVo();
                videoHotnumInVo.setType(HotNumType.ATTENT.getCode());
                videoHotnumInVo.setVideoId(userAttentInVo.getVideoId());
                videoFeignClient.updateVideoHotnum(videoHotnumInVo);
            }
        }else if(userAttentInVo.getType() == 0){
            //取消关注
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("toUserId", toUserId);
            map.put("type", 1);
            int num = userUserRelationMapper.deleteRelation(map);
            if(num >0){
                userInfoService.handleFanNum(toUserId, -1L);
                userInfoService.handleLookNum(userId, -1L);
            }
        }
        return R.ok(MsgInfo.成功.toString());
    }

    /**
     * 用户拉黑
     * @param userAddBlackInVo
     * @param request
     * @return
     */
    @Override
    public R addBlack(UserAddBlackInVo userAddBlackInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        if(userInfoEntity == null ||userInfoEntity.getUserId() == null)
            return R.error(ErrorCode.TokenInvalid);
        Long userId = userInfoEntity.getUserId();
        Long toUserId = userAddBlackInVo.getUserId();
        if(toUserId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"userId参数不能空");
        if(userAddBlackInVo.getType() == 1){//拉黑
            if(!checkRelation(userId, toUserId,2)){
                deleteRelation(userId, toUserId);
                UserUserRelationEntity userRelationEntity = new UserUserRelationEntity();
                userRelationEntity.setUserId(userId);
                userRelationEntity.setToUserId(toUserId);
                userRelationEntity.setType(2);
                userRelationEntity.setCreateTime(new Date());
                save(userRelationEntity);
            }
        }else if(userAddBlackInVo.getType() == 0){
            //取消拉黑
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("toUserId", toUserId);
            map.put("type", 2);
            userUserRelationMapper.deleteRelation(map);
        }
        return R.ok(MsgInfo.成功.toString());
    }

    @Override
    public UserUserRelationEntity findRelation(Long userId, Long toUserId, Integer type) {
        return null;
    }

    /**
     * 删除用户之间关系
     * @param userId
     * @param toUserId
     */
    public void deleteRelation(Long userId,Long toUserId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("toUserId", toUserId);
        userUserRelationMapper.deleteRelation(map);
    }
}
