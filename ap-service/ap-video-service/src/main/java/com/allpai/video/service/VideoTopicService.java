package com.allpai.video.service;

import com.allpai.common.utils.R;
import com.allpai.entity.VideoTopicInfoEntity;
import com.allpai.entity.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 23:00
 * 短视频话题
 */
public interface VideoTopicService {
    VideoTopicInfoEntity queryObject(Long topicId);

    List<VideoTopicInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(VideoTopicInfoEntity videoTopic);

    void update(VideoTopicInfoEntity videoTopic);

    void delete(Long topicId);

    void deleteBatch(Long[] topicIds);

    R add(VideoTopicAddInVo videoTopicAddInVo, HttpServletRequest request);

    R hotTopicRank(HttpServletRequest request);

    R videoTopicShare(VideoTopicFindInfoOutVo videoTopicFindInfoOutVo, HttpServletRequest request);

    R findTopicInfo(VideoTopicFindInfoInVo videoTopicFindInfoInVo, HttpServletRequest request);

    R findTopicTitleInfo(VideoTopicTitleInfoInVo videoTopicTitleInfoInVo, HttpServletRequest request);

    void updatePartakeNum(Long topicId,Long userId);

    void handleTopicHotNum(Long topicId,int type);

    R topicShare(TopicShareNumInVo topicShareNumInVo, HttpServletRequest request);

    R findTopicInfoWeb(HttpServletRequest request);
}
