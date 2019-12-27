package com.allpai.video.service.impl;

import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.MsgInfo;
import com.allpai.common.utils.PageUtils;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.common.vo.HomePageHotSearchInVo;
import com.allpai.entity.common.vo.HomePageSearchInVo;
import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.dto.VideoInfoDto;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.video.vo.*;
import com.allpai.video.mapper.VideoInfoMapper;
import com.allpai.video.service.VideoInfoService;
import com.allpai.video.service.VideoTopicService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:35
 * 短视频信息
 */
@Service("videoInfoService")
public class VideoInfoServiceImpl implements VideoInfoService {
    @Autowired
    private VideoInfoMapper videoInfoMapper;
    @Autowired
    private VideoTopicService videoTopicService;
    @Override
    public VideoInfoEntity queryObject(Long videoId) {
        return videoInfoMapper.queryObject(videoId);
    }

    @Override
    public List<VideoInfoEntity> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(VideoInfoEntity videoInfo) {

    }

    @Override
    public void update(VideoInfoEntity videoInfo) {

    }

    @Override
    public void delete(Long videoId) {

    }

    @Override
    public void deleteBatch(Long[] videoIds) {

    }

    @Override
    public R deleteBatchApp(Long[] videoIds) {
        return null;
    }

    @Override
    public Long getGoogNumTotal(Long userId) {
        return null;
    }

    @Override
    public R worklist(VideoWorkListInVo videoWorkListInVo, HttpServletRequest request) {
        return null;
    }

    /**
     * 首页推荐列表
     * @param videoHotListInVo
     * @param request
     * @return
     */
    @Override
    public R hotlist(VideoHotListInVo videoHotListInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Integer page = videoHotListInVo.getPage();
        Integer limit = videoHotListInVo.getLimit();
        Map<String, Object> map = new HashMap<>();
        Random rand = new Random();
        //推荐列表
        List<VideoInfoDto> videoInfoList = new ArrayList<>();
        if(userInfoEntity != null && userInfoEntity.getUserId() != null)
            map.put("userId", userInfoEntity.getUserId());
        //查找热门
        limit = 10;
        int hotStart = rand.nextInt(550);
        map.put("offset", hotStart);
        map.put("limit", limit);
        List<VideoInfoDto> hotList = videoInfoMapper.queryHotlist(map);
        videoInfoList.addAll(hotList);
        //查找普通
		/*int comStart = rand.nextInt(30);
		limit = 3;
		map.put("offset", comStart);
		map.put("limit", limit);
		List<VideoInfoDto> commonList = videoInfoDao.queryCommonList(map);
		videoInfoList.addAll(commonList);*/
        //查找最新
		/*limit = 10;
		int newStart = rand.nextInt(300);
		map.put("offset", newStart);
		map.put("limit", limit);
		List<VideoInfoDto> newList = videoInfoDao.queryNewList(map);
		videoInfoList.addAll(newList);*/

        //根据时间和热度查找数据 ,上线后切换
		/*limit = 3;
		map.put("time", "2018-12-12 12:00:00");
		int timeTotal = videoInfoDao.queryTimeTotal(map);
		int timeStart = rand.nextInt(timeTotal - 2);
		map.put("offset", timeStart);
		map.put("limit", limit);
		System.out.println("offset:" + timeStart);
		System.out.println("limit:" + limit );
		List<VideoInfoDto> newList = videoInfoDao.queryTimeList(map);
		videoInfoList.addAll(newList);*/
        //只搜索一个视频
		/*Map<String, Object> mapObj = new HashMap<>();
		mapObj.put("offset", 0);
		mapObj.put("limit", 1);
		mapObj.put("videoId", 296);
		List<VideoInfoDto> videoInfos = videoInfoDao.queryVideoShareInfo(mapObj);
		videoInfoList.addAll(videoInfos);*/

        //查询范围内的视频
		/*Map<String, Object> rangObj = new HashMap<>();
		rangObj.put("offset", 0);
		rangObj.put("limit", 15);
		rangObj.put("videoIds", "1035,1036,1037,1040,1041,1033,1034,1032,1031,1030,1029,1049");
		List<VideoInfoDto> rangLists = videoInfoDao.queryRangeList(rangObj);
		videoInfoList.addAll(rangLists);*/

        int total = 0;
//		limit = 10;
        Collections.shuffle(videoInfoList);
        PageUtils pageUtil = new PageUtils(videoInfoList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 首页关注视频列表
     * @param videoLookListInVo
     * @param request
     * @return
     */
    @Override
    public R looklist(VideoLookListInVo videoLookListInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Integer page = videoLookListInVo.getPage();
        Integer limit = videoLookListInVo.getLimit();
        Map<String, Object> map = new HashMap<>();
        if(userInfoEntity == null || userInfoEntity.getUserId() == null)
            return R.error(ErrorCode.TokenInvalid);
        //关注列表
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);
        map.put("userId", userInfoEntity.getUserId());
        //查询关注视频
        List<VideoInfoDto> videoInfoList = videoInfoMapper.queryLooklist(map);
        int total = videoInfoMapper.queryLookTotal(map);
        PageUtils pageUtil = new PageUtils(videoInfoList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 视频发布
     * @param videoPublishInVo
     * @param request
     * @return
     */
    @Override
    public R publish(VideoPublishInVo videoPublishInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        VideoInfoEntity videoInfoEntity = new VideoInfoEntity();
        if(!StringUtils.isBlank(videoPublishInVo.getCover()))
            videoInfoEntity.setCover(videoPublishInVo.getCover());
        if(videoPublishInVo.getSeeAuth() == null)
            return R.error(ErrorCode.ParamInvalid.getCode(),"seeAuth参数不能空");
        videoInfoEntity.setSeeAuth(videoPublishInVo.getSeeAuth());
        if(videoPublishInVo.getStoreType()== null)
            return R.error(ErrorCode.ParamInvalid.getCode(),"storeType参数不能空");
        videoInfoEntity.setStoreType(videoPublishInVo.getStoreType());
        videoInfoEntity.setUserId(userInfoEntity.getUserId());
        videoInfoEntity.setContent(handleStringEmpty(videoPublishInVo.getContent()));
        videoInfoEntity.setPlace(handleStringEmpty(videoPublishInVo.getPlace()));
        videoInfoEntity.setPlaceDetail(handleStringEmpty(videoPublishInVo.getPlaceDetail()));
        videoInfoEntity.setLastTime(new Date());
        videoInfoEntity.setCreateTime(new Date());
        if(!StringUtils.isBlank(videoPublishInVo.getVodVideoId()))
            videoInfoEntity.setVodVideoId(videoPublishInVo.getVodVideoId());
        videoInfoEntity.setVideoUrl("");
        String musicName = videoPublishInVo.getMusicName();
        if(musicName == null) musicName = "";
        videoInfoEntity.setMusicName(musicName);
        Long topicId = videoPublishInVo.getTopicId();
        if(topicId == null){
            topicId = 0L;
        }else{
            //更新参与人数
            videoTopicService.updatePartakeNum(topicId, userId);
            videoTopicService.handleTopicHotNum(topicId, 2);
        }
        if(topicId < 0)return R.error(ErrorCode.ParamInvalid.getCode(),"topicId参数不正确");
        videoInfoEntity.setTopicId(topicId);
        videoInfoMapper.saveBase(videoInfoEntity);
        return R.ok(MsgInfo.成功.toString());
    }

    /**
     * 喜爱视频列表
     * @param videoLikeListInVo
     * @param request
     * @return
     */
    @Override
    public R likelist(VideoLikeListInVo videoLikeListInVo, HttpServletRequest request) {
        Long userId = videoLikeListInVo.getUserId();
        if(userId == null){
            UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
            userId = userInfoEntity.getUserId();
            if(userId == null)return R.error(ErrorCode.TokenInvalid);
        }
        Integer page = videoLikeListInVo.getPage();
        Integer limit = videoLikeListInVo.getLimit();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);
        map.put("userId", userId);
        //查询列表数据
        List<VideoInfoDto> videoInfoList = videoInfoMapper.queryLikeList(map);
        int total = videoInfoMapper.queryLikeTotal(map);
        PageUtils pageUtil = new PageUtils(videoInfoList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    @Override
    public R videoShare(Long videoId, HttpServletRequest request) {
        return null;
    }

    @Override
    public R findVideoInfo(Long videoId, HttpServletRequest request) {
        return null;
    }

    @Override
    public void updateVideoHotnum(int type, Long videoId) {

    }

    @Override
    public R findTopicVideoList(VideoTopicFindListInVo videoTopicFindListInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R findVcaData(VideoVcaInVo videoVcaInVo) {
        return null;
    }

    @Override
    public R homePageSearch(HomePageSearchInVo homePageSearchInVo, HttpServletRequest request) {
        return null;
    }

    @Override
    public R homePageHotSearch(HomePageHotSearchInVo homePageHotSearchInVo, HttpServletRequest request) {
        return null;
    }

    private String handleStringEmpty(String param){
        if(param == null) return "";
        return param;
    }
}
