package com.allpai.video.service;

import com.allpai.common.utils.R;
import com.allpai.entity.VideoCommentEntity;
import com.allpai.entity.vo.UserCommentDeleteInVo;
import com.allpai.entity.vo.UserCommentListInVo;
import com.allpai.entity.vo.VideoCommentAddInVo;
import com.allpai.entity.vo.VideoCommentListInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:23
 * 短视频评论
 */
public interface VideoCommentService {
    VideoCommentEntity queryObject(Long commentId);

    List<VideoCommentEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(VideoCommentEntity videoComment);

    void update(VideoCommentEntity videoComment);

    void delete(Long commentId);

    void deleteBatch(Long[] commentIds);

    R add(VideoCommentAddInVo videoCommentAddInVo, HttpServletRequest request);

    R list(VideoCommentListInVo videoCommentListInVo, HttpServletRequest request);

    void updateGoodInfo(Long commentId,Long type);

    R delete(UserCommentDeleteInVo userCommentDeleteInVo);

    void deleteByVideoIds(Long[] videoIds);

    R userCommentList(UserCommentListInVo userCommentListInVo, HttpServletRequest request);
}
