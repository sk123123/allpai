package com.allpai.music.service;

import com.allpai.common.utils.R;
import com.allpai.entity.VideoMusicEntity;
import com.allpai.entity.vo.MusicListInVo;
import com.allpai.entity.vo.MusicSearchInVo;
import com.allpai.entity.vo.MusicUploadInVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/14 0014 16:14
 */
public interface VideoMusicService {
    VideoMusicEntity queryObject(Long musicId);

    List<VideoMusicEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(VideoMusicEntity videoMusic);

    void update(VideoMusicEntity videoMusic);

    void delete(Long musicId);

    void deleteBatch(Long[] musicIds);

    R musicList(MusicListInVo videoMusicListInVo, HttpServletRequest request);

    R musicSearch(MusicSearchInVo musicSearchInVo, HttpServletRequest request);

    R musicUpload(MusicUploadInVo musicUploadInVo, HttpServletRequest request);
}
