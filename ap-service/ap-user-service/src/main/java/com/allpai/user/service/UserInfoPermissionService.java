package com.allpai.user.service;

import com.allpai.common.utils.R;
import com.allpai.entity.common.vo.UserVideoLikeInVo;
import com.allpai.entity.user.vo.UserAttentInVo;
import com.allpai.entity.user.vo.UserCommentControlInVo;
import com.allpai.entity.video.vo.VideoCommentAddInVo;
import com.allpai.entity.video.vo.VideoShareInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 20:39
 */
public interface UserInfoPermissionService {
    //用户黑名单权限
    Map<String,Object> blacklistPermission(VideoCommentAddInVo videoCommentListInVo, HttpServletRequest request);

    //用户评论控制
    R commentControlPermission(UserCommentControlInVo userCommentControlInVo , HttpServletRequest request);

    Map<String,Object> likePermission(UserVideoLikeInVo userVideoLikeInVo, HttpServletRequest request);

    R sharePermission(VideoShareInVo videoShareInVo, HttpServletRequest request);

    R attentPermission(UserAttentInVo userAttentInVo, HttpServletRequest request);
}
