package com.allpai.video.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.video.VideoCommentEntity;
import com.allpai.entity.user.dto.UserCommentDto;
import com.allpai.entity.video.dto.VCommentSonDto;
import com.allpai.entity.video.dto.VideoCommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:18
 * 短视频评论
 */
@Mapper
public interface VideoCommentMapper extends BaseMapper<VideoCommentEntity> {
    List<VideoCommentDto> queryParentList(Map<String, Object> map);

    int queryParentTotal(Map<String, Object> map);

    List<VCommentSonDto> querySonList(Map<String, Object> map);

    int querySonTotal(Map<String, Object> map);

    int deleteSonsBatch(Long[] commentIds);

    void deleteByVideoIds(Long[] videoIds);

    List<UserCommentDto> queryUserCommentList(Map<String, Object> map);

    int queryUserCommentTotal(Map<String, Object> map);

    int findCommentNoReadNum(Long userId);
}
