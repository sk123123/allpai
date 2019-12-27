package com.allpai.video.service;

import com.allpai.common.utils.R;
import com.allpai.entity.common.vo.HomePageHotSearchInVo;
import com.allpai.entity.common.vo.HomePageSearchInVo;
import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:27
 * 短视频信息
 */
public interface VideoInfoService {
    VideoInfoEntity queryObject(Long videoId);

    List<VideoInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(VideoInfoEntity videoInfo);

    void update(VideoInfoEntity videoInfo);

    void delete(Long videoId);

    void deleteBatch(Long[] videoIds);

    R deleteBatchApp(Long[] videoIds);

    Long  getGoogNumTotal(Long userId);

    R worklist(VideoWorkListInVo videoWorkListInVo, HttpServletRequest request);

    R hotlist(VideoHotListInVo videoHotListInVo, HttpServletRequest request);

    R looklist(VideoLookListInVo videoLookListInVo, HttpServletRequest request);

    R publish(VideoPublishInVo videoPublishInVo, HttpServletRequest request);

    R likelist(VideoLikeListInVo videoLikeListInVo, HttpServletRequest request);

    R videoShare(Long videoId,HttpServletRequest request);

    R findVideoInfo(Long videoId,HttpServletRequest request);

    void updateVideoHotnum(int type,Long videoId);

    R findTopicVideoList(VideoTopicFindListInVo videoTopicFindListInVo, HttpServletRequest request);

    R findVcaData(VideoVcaInVo videoVcaInVo);

    R homePageSearch(HomePageSearchInVo homePageSearchInVo, HttpServletRequest request);

    R homePageHotSearch(HomePageHotSearchInVo homePageHotSearchInVo, HttpServletRequest request);
}
