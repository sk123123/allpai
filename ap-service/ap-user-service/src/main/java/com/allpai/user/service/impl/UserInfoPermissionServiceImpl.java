package com.allpai.user.service.impl;

import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.MsgInfo;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.common.vo.UserVideoLikeInVo;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.user.vo.UserAttentInVo;
import com.allpai.entity.user.vo.UserCommentControlInVo;
import com.allpai.entity.user.vo.UserFindInfoInVo;
import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.vo.VideoCommentAddInVo;
import com.allpai.entity.video.vo.VideoShareInVo;
import com.allpai.user.feignConfig.VideoFeignClient;
import com.allpai.user.mapper.UserInfoMapper;
import com.allpai.user.mapper.UserUserRelationMapper;
import com.allpai.user.service.UserInfoPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 20:42
 */
@Service("UserInfoPermissionService")
public class UserInfoPermissionServiceImpl implements UserInfoPermissionService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private VideoFeignClient videoFeignClient;
    @Autowired
    private UserUserRelationMapper userUserRelationMapper;

    @Override
    public Map<String, Object> blacklistPermission(VideoCommentAddInVo videoCommentListInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long toUserId = userInfoEntity.getUserId();
        Map<String, Object> map = new HashMap<>();
        //获取当前短视频Id
        Long videoId = videoCommentListInVo.getVideoId();
        VideoInfoEntity videoInfoEntity = videoFeignClient.queryObject(videoId);
        //用户评论Id
        Long userId = videoInfoEntity.getUserId();
        userInfoEntity = (UserInfoEntity) userInfoMapper.queryObject(userId);
        Integer type = userInfoEntity.getCommentControl();
        //获取用户关系表userUserId
        Long userUserId = userUserRelationMapper.queryuserUserId(toUserId, userId, 2);
        //黑名单(用户评论控制)
        if (userUserId == null) {
            if (type == 1) {
                map.put("falg", false);
                map.put("msg", "该用户禁止了所有人的评论哦~");
                return map;
            } else {
                map.put("falg", true);
                return map;
            }
        } else {
            map.put("falg", false);
            map.put("msg", "很抱歉该用户已经将您拉黑了~");
            return map;
        }
    }

    /**
     * 用户评论控制
     *
     * @return
     */
    @Override
    public R commentControlPermission(UserCommentControlInVo userCommentControlInVo, HttpServletRequest request) {
        //comment_control默认为0
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if (userId == null) return R.error(ErrorCode.TokenInvalid);
        Integer type = userCommentControlInVo.getType();
        if (type == 1 || type == 0) {
            userInfoEntity.setCommentControl(type);
            userInfoMapper.update(userInfoEntity);
            SessionUtils.setSessionUser(request, userInfoEntity);
            return R.ok(MsgInfo.成功.toString());
        } else {
            return R.error(ErrorCode.ParamInvalid.getCode(), "type参数无效");
        }
    }

    /**
     * 点赞权限
     *
     * @return
     */
    @Override
    public Map<String, Object> likePermission(UserVideoLikeInVo userVideoLikeInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        Long videoId = userVideoLikeInVo.getVideoId();
        Integer type = userVideoLikeInVo.getType();
        VideoInfoEntity videoInfoEntity = videoFeignClient.queryObject(videoId);
        //被关注的用户ID
        Long toUserId = videoInfoEntity.getUserId();
        //获取用户关系表userUserId
        Long userUserId = userUserRelationMapper.queryuserUserId(toUserId, userId, 2);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userUserId == null) {
            //类型   0 取消点赞  1 点赞
            if (type == 0 || type == 1)
                map.put("falg", true);
            return map;
        } else {
            map.put("falg", false);
            map.put("msg", "很抱歉该用户已经将您拉黑了~");
            return map;
        }
    }

    /**
     * 视频转发权限(用户登录和未登录都能进行视频转发)
     *
     * @return
     */
    @Override
    public R sharePermission(VideoShareInVo videoShareInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        Long videoId = videoShareInVo.getVideoId();
        VideoInfoEntity videoInfoEntity = videoFeignClient.queryObject(videoId);
        //被关注的用户ID
        Long toUserId = videoInfoEntity.getUserId();
        //获取用户关系表userUserId
        Long userUserId = userUserRelationMapper.queryuserUserId(toUserId, userId, 2);
        if (userUserId != null) return R.error(ErrorCode.ParamInvalid.getCode(), "很抱歉该用户已经将您拉黑了~");
        return R.ok(MsgInfo.成功.toString());
    }

    /**
     * 用户关注(黑名单)
     *
     * @return
     */
    @Override
    public R attentPermission(UserAttentInVo userAttentInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        //用户Id
        Long userId = userInfoEntity.getUserId();
        //被关注的用户ID
        Long toUserId = userAttentInVo.getUserId();
        //类型 0 取消关注 1关注
        Integer type = userAttentInVo.getType();
        Long userUserId = userUserRelationMapper.queryuserUserId(toUserId, userId, 2);
        if (userUserId == null) {
            if (type == 0 || type == 1) return R.ok(MsgInfo.成功.toString());
        } else {
            return R.error(ErrorCode.ParamInvalid.getCode(), "很抱歉该用户已经将您拉黑了~");
        }
        return R.ok(MsgInfo.成功.toString());
    }

    /**
     * 用户主页
     *
     * @return
     */
    public R findUserInfoPermission(UserFindInfoInVo userFindInfoInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        //用户Id
        Long userId = userInfoEntity.getUserId();
        //被关注的用户ID
        Long toUserId = userFindInfoInVo.getUserId();
        Long userUserId = userUserRelationMapper.queryuserUserId(toUserId, userId, 2);
        if (userUserId != null) return R.error(ErrorCode.ParamInvalid.getCode(), "很抱歉该用户已经将您拉黑了~");
        return R.ok(MsgInfo.成功.toString());
    }
}
///**
// * 是否能发私信
// * @return
// */
