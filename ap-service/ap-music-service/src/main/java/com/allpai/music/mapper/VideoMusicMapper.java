package com.allpai.music.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.dto.VideoMusicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:12
 * 短视频音乐
 */
@Mapper
public interface VideoMusicMapper extends BaseMapper {
    List<VideoMusicDto> queryMusicList(Map<String, Object> map);

    int queryMusicTotal(Map<String, Object> map);

    List<VideoMusicDto> musicSearch(Map<String, Object> map);

    int musicSearchTotal(Map<String, Object> map);
}
