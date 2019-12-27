package com.allpai.video.service.impl;

import com.allpai.common.constant.HotNumType;
import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.MsgInfo;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.common.UserVideoRelationEntity;
import com.allpai.entity.common.vo.UserVideoLikeInVo;
import com.allpai.entity.common.vo.VideoDisLikeInVo;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.vo.VideoHotnumInVo;
import com.allpai.entity.video.vo.VideoLikeNotifiyInVo;
import com.allpai.entity.video.vo.VideoShareInVo;
import com.allpai.video.feignClient.UserFeignClient;
import com.allpai.video.mapper.UserVideoRelationMapper;
import com.allpai.video.mapper.VideoInfoMapper;
import com.allpai.video.service.UserVideoRelationService;
import com.allpai.video.service.VideoInfoService;
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
 * @date 2019/12/26 0026 20:31
 */
@Service("userVideoRelationService")
public class UserVideoRelationServiceImpl implements UserVideoRelationService {
    @Autowired
    private UserVideoRelationMapper userVideoRelationMapper;

    @Autowired
    private VideoInfoMapper videoInfoMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private VideoInfoService videoInfoService;

    @Override
    public UserVideoRelationEntity queryObject(Long userVideoId){
        return userVideoRelationMapper.queryObject(userVideoId);
    }

    @Override
    public List<UserVideoRelationEntity> queryList(Map<String, Object> map){
        return userVideoRelationMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map){
        return userVideoRelationMapper.queryTotal(map);
    }

    @Override
    public void save(UserVideoRelationEntity userVideoRelation){
        userVideoRelationMapper.save(userVideoRelation);
    }

    @Override
    public void update(UserVideoRelationEntity userVideoRelation){
        userVideoRelationMapper.update(userVideoRelation);
    }

    @Override
    public void delete(Long userVideoId){
        userVideoRelationMapper.delete(userVideoId);
    }

    @Override
    public void deleteBatch(Long[] userVideoIds){
        userVideoRelationMapper.deleteBatch(userVideoIds);
    }

    public UserVideoRelationEntity findSingleUserVideoRelationEntity(Long userId,Long videoId,Integer type){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", 0);
        map.put("limit", 1);
        map.put("userId", userId);
        map.put("videoId", videoId);
        map.put("type", type);
        List<UserVideoRelationEntity> list = queryList(map);
        if(list != null  && list.size() >0) return list.get(0);
        return null;
    }

    /**
     * 清除喜欢和不喜欢的关系
     */
    public void  deletelikeAndNoLikeRelation(Long userId,Long videoId ){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("videoId", videoId);
        userVideoRelationMapper.deleteRelation(map);
    }

    /**
     * 视频点赞
     * @param userVideoLikeInVo
     * @param request
     * @return
     */
    @Override
    public R like(UserVideoLikeInVo userVideoLikeInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        Long videoId = userVideoLikeInVo.getVideoId();
        Integer type = userVideoLikeInVo.getType();
        if(userId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"userId参数不能空");
        if(videoId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"videoId参数不能空");
        //检查是否有点赞权限
        Map<String,Object> mapLike = userFeignClient.likePermission(userVideoLikeInVo, request);
        boolean falg = (boolean)mapLike.get("falg");
        if(!falg)return R.error(ErrorCode.InfoError.getCode(),(String)mapLike.get("msg"));

        //点赞
        if(type == 1){
            UserVideoRelationEntity dbUserVideoRelationEntity = findSingleUserVideoRelationEntity(userId, videoId, 1);

            if(dbUserVideoRelationEntity == null){
                //喜欢和不喜欢只能存一，清除存在的关系
                deletelikeAndNoLikeRelation(userId, videoId);
                UserVideoRelationEntity userVideoRelationEntity = new UserVideoRelationEntity();
                userVideoRelationEntity.setUserId(userId);
                userVideoRelationEntity.setVideoId(videoId);
                userVideoRelationEntity.setCreateTime(new Date());
                userVideoRelationEntity.setType(1);
                userVideoRelationMapper.save(userVideoRelationEntity);

                VideoInfoEntity videoInfoEntity = videoInfoMapper.queryObject(videoId);
                Long goodNum = videoInfoEntity.getGoodNum() + 1 ;
                videoInfoEntity.setGoodNum(goodNum);
                videoInfoMapper.update(videoInfoEntity);

                //点赞通知
                VideoLikeNotifiyInVo videoLikeNotifiyInVo = new VideoLikeNotifiyInVo();
                videoLikeNotifiyInVo.setVideoId(videoId);
                videoLikeNotifiyInVo.setNickName(userInfoEntity.getNickName());
                userFeignClient.videoLikeNotifiy(videoLikeNotifiyInVo);

                //更新热度值
                videoInfoService.updateVideoHotnum(HotNumType.LIKE.getCode(), videoId);
            }
            //取消点赞
        }else if(type == 0){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("videoId", videoId);
            map.put("type", 1);
            int num =userVideoRelationMapper.deleteRelation(map);
            if(num > 0){
                VideoInfoEntity videoInfoEntity = videoInfoMapper.queryObject(videoId);
                Long goodNum = videoInfoEntity.getGoodNum() - 1 ;
                videoInfoEntity.setGoodNum(goodNum);
                videoInfoMapper.update(videoInfoEntity);
            }
        }
        return R.ok(MsgInfo.成功.toString());
    }



    @Override
    public R dislike(VideoDisLikeInVo videoDisLikeInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        Long videoId = videoDisLikeInVo.getVideoId();
        if(videoId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"videoId参数不能空");

        UserVideoRelationEntity dbUserVideoRelationEntity = findSingleUserVideoRelationEntity(userId, videoId, 2);
        if(dbUserVideoRelationEntity == null){
            //喜欢和不喜欢只能存一，清除存在的关系
            deletelikeAndNoLikeRelation(userId, videoId);
            UserVideoRelationEntity userVideoRelationEntity = new UserVideoRelationEntity();
            userVideoRelationEntity.setUserId(userId);
            userVideoRelationEntity.setVideoId(videoId);
            userVideoRelationEntity.setCreateTime(new Date());
            userVideoRelationEntity.setType(2);
            userVideoRelationMapper.save(userVideoRelationEntity);
        }
        return R.ok(MsgInfo.成功.toString());
    }

    @Override
    public R share(VideoShareInVo videoShareInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long videoId = videoShareInVo.getVideoId();
        if(videoId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"videoId参数不能空");
        if(userInfoEntity != null){
            Long userId = userInfoEntity.getUserId();
            UserVideoRelationEntity dbUserVideoRelationEntity = findSingleUserVideoRelationEntity(userId, videoId, 3);
            if(dbUserVideoRelationEntity == null){
                UserVideoRelationEntity userVideoRelationEntity = new UserVideoRelationEntity();
                userVideoRelationEntity.setUserId(userId);
                userVideoRelationEntity.setVideoId(videoId);
                userVideoRelationEntity.setCreateTime(new Date());
                userVideoRelationEntity.setType(3);
                userVideoRelationEntity.setTotalNum(1);
                userVideoRelationMapper.save(userVideoRelationEntity);
            }else{
                dbUserVideoRelationEntity.setTotalNum(dbUserVideoRelationEntity.getTotalNum()+ 1);
                userVideoRelationMapper.update(dbUserVideoRelationEntity);
            }
        }

        VideoInfoEntity videoInfoEntity = videoInfoMapper.queryObject(videoId);
        Long shareNum = videoInfoEntity.getShareNum();
        shareNum = shareNum + 1;
        videoInfoEntity.setShareNum(shareNum);
        videoInfoMapper.update(videoInfoEntity);
        //更新热度值
        videoInfoService.updateVideoHotnum(HotNumType.SHARE.getCode(), videoId);
        return R.ok(MsgInfo.成功.toString()).put("shareNum", shareNum);
    }



}
