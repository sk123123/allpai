package com.allpai.video.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.VideoInfoEntity;
import com.allpai.entity.dto.VideoInfoDto;
import com.allpai.entity.dto.VideoTopicInfoDto;
import com.allpai.entity.vo.ComprehensiveSearchOutVo;
import com.allpai.entity.vo.HomePageHotSearchOutVo;
import com.allpai.entity.vo.TopicSearchOutVo;
import com.allpai.entity.vo.VideoSearchOutVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:05
 * 短视频信息
 */
@Mapper
public interface VideoInfoMapper extends BaseMapper<VideoInfoEntity> {
    long getGoogNumTotal(long userId);

    List<VideoInfoDto> queryLooklist(Map<String, Object> map);

    int queryLookTotal(Map<String, Object> map);

    int saveBase(VideoInfoEntity videoInfoEntity);

    List<VideoInfoDto> queryLikeList(Map<String, Object> map);

    int queryLikeTotal(Map<String, Object> map);

    int updateByVod(VideoInfoEntity videoInfoEntity);

    List<VideoInfoDto> queryVideoShareInfo(Map<String, Object> map);

    List<VideoInfoEntity> queryListByVideoIds(Long[] videoIds);

    VideoInfoDto queryVideoDetailInfo(Map<String, Object> map);

    List<VideoInfoDto> queryHotlist(Map<String, Object> map);

    List<VideoInfoDto> queryCommonList(Map<String, Object> map);

    List<VideoInfoDto> queryNewList(Map<String, Object> map);

    List<VideoInfoDto> queryTimeList(Map<String, Object> map);

    List<VideoInfoDto> queryRangeList(Map<String, Object> map);

    int queryTimeTotal(Map<String, Object> map);

    List<VideoTopicInfoDto> queryTopicVideoList(Map<String, Object> map);

    int queryTopicVideoTotal(Map<String, Object> map);

    List<ComprehensiveSearchOutVo> queryComprehensiveList(Map<String, Object> map);

    List<VideoSearchOutVo> queryVideoSearchList(Map<String, Object> map);

    int queryVideoSearchListTotal(String content);

    List<TopicSearchOutVo> queryTopicSearchOutVo(Map<String, Object> map);

   int queryTopicSearchTotal(String content);

    List<HomePageHotSearchOutVo> queryHomePageHotSearchOutVo(Map<String, Object> map);

    VideoInfoEntity findVideobyTopicUserId(Map<String, Object> map);
}
