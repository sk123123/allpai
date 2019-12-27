package com.allpai.video.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.video.VideoTopicInfoEntity;
import com.allpai.entity.video.dto.TopicInfoDetailDto;
import com.allpai.entity.video.dto.VideoTopicInfoDto;
import com.allpai.entity.video.vo.VideoHotTopicRankOutVo;
import com.allpai.entity.video.vo.VideoTopicAddOutVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 22:55
 * 短视频话题
 */
@Mapper
public interface VideoTopicMapper extends BaseMapper<VideoTopicInfoEntity> {
    //根据话题的标题查询
    List<VideoTopicInfoDto> queryTopicTitleInfo(Map<String, Object> map);
    //根据话题Id查询
    TopicInfoDetailDto queryTopicInfo(Long topicId);

    List<VideoHotTopicRankOutVo> queryObjectList(Map<String, Object> map);

    int topicTitleTotal(String topicTitle);

    VideoTopicAddOutVo queryTopicTitleAddInfo(Map<String, Object> map);

    int queryPartakeNum(Long topicId);

    void updatePartakeNum(Map<String, Object> map);
}
