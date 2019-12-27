package com.allpai.video.service.impl;

import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.CheckTextUtils;
import com.allpai.common.utils.PageUtils;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.video.VideoInfoEntity;
import com.allpai.entity.video.VideoTopicInfoEntity;
import com.allpai.entity.video.dto.TopicInfoDetailDto;
import com.allpai.entity.video.dto.VideoTopicInfoDto;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.video.vo.*;
import com.allpai.video.mapper.VideoInfoMapper;
import com.allpai.video.mapper.VideoTopicMapper;
import com.allpai.video.service.UserTopicRelationService;
import com.allpai.video.service.VideoTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 23:02
 * 短视频话题
 */
@Service("videoTopicService")
public class VideoTopicServiceImpl implements VideoTopicService {
    @Autowired
    private VideoInfoMapper videoInfoMapper;
    @Autowired
    private VideoTopicMapper videoTopicMapper;
    @Autowired
    private UserTopicRelationService userTopicRelationService;
    @Override
    public VideoTopicInfoEntity queryObject(Long topicId) {
        return videoTopicMapper.queryObject(topicId);
    }

    @Override
    public List<VideoTopicInfoEntity> queryList(Map<String, Object> map) {
        return videoTopicMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return videoTopicMapper.queryTotal(map);
    }

    @Override
    public void save(VideoTopicInfoEntity videoTopic) {
        videoTopicMapper.save(videoTopic);
    }

    @Override
    public void update(VideoTopicInfoEntity videoTopic) {
        videoTopicMapper.update(videoTopic);
    }

    @Override
    public void delete(Long topicId) {
        videoTopicMapper.delete(topicId);
    }

    @Override
    public void deleteBatch(Long[] topicIds) {
        videoTopicMapper.deleteBatch(topicIds);
    }

    /**
     * 短视频话题新增
     * @param videoTopicAddInVo
     * @param request
     * @return
     */
    @Override
    public R add(VideoTopicAddInVo videoTopicAddInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        VideoTopicInfoEntity videoTopicEntity = new VideoTopicInfoEntity();
        Map<String,Object> map = new HashMap<>();
        String topicTitle = videoTopicAddInVo.getTopicTitle();
        //检查内容是否健康
        if(!CheckTextUtils.checkData(topicTitle))return R.error(ErrorCode.InfoError.getCode(),"输入话题失败，请您文明用语");
        if(topicTitle.length()>30) {
            return R.error(ErrorCode.InfoError.getCode(),"话题名称过长，请您重新输入!");
        }else {
            //1、查询话题(名称是否相同)
            map.put("topicTitle", topicTitle);
            VideoTopicAddOutVo videoTopicAddOutVo = videoTopicMapper.queryTopicTitleAddInfo(map);
            if(videoTopicAddOutVo != null ) {
                //2、存在当前话题(增加参与人数)
                Long topicId = videoTopicAddOutVo.getTopicId();
                updatePartakeNum(topicId,userId);
                return R.ok().put("topicId", topicId);
            }else {
                //3、不存在创建新的话题
                videoTopicEntity.setTopicTitle(videoTopicAddInVo.getTopicTitle());
                videoTopicEntity.setCover(userInfoEntity.getHeadUrl());
                videoTopicEntity.setHotNum(0L);
                videoTopicEntity.setIntroduce(videoTopicAddInVo.getIntroduce());
                videoTopicEntity.setCreateTime(new Date());
                videoTopicEntity.setUserId(userId);
                videoTopicEntity.setShareNum(0L);
                videoTopicEntity.setPartakeNum(0L);
                videoTopicMapper.save(videoTopicEntity);
                //4、存储用户与话题关系表中
                userTopicRelationService.saveRelation(videoTopicEntity.getTopicId(),videoTopicEntity.getUserId());
                return R.ok().put("topicId", videoTopicEntity.getTopicId());
            }
        }
    }

    /**
     * 获取热门话题
     * @param request
     * @return
     */
    @Override
    public R hotTopicRank(HttpServletRequest request) {
//        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
//        Long userId = userInfoEntity.getUserId();
//        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        Map<String,Object> map = new HashMap<>();
        map.put("offset", 0);
        map.put("limit", 10);
        //根据热度值排序获取前十的视频话题
        List<VideoHotTopicRankOutVo> list = videoTopicMapper.queryObjectList(map);
        return R.ok().put("data", list);
    }

    /**
     * 短视频话题分享？？？？
     * @param videoTopicFindInfoOutVo
     * @param request
     * @return
     */
    @Override
    public R videoTopicShare(VideoTopicFindInfoOutVo videoTopicFindInfoOutVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        Long topicId = videoTopicFindInfoOutVo.getTopicId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        if(topicId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"topicId参数不能空");
        //获取该话题
        VideoTopicInfoEntity videoTopicEntity = queryObject(topicId);

        if(videoTopicEntity == null){
            return R.error(ErrorCode.InfoError.getCode(),"此话题已删除");
        }
        return R.ok().put("data", videoTopicEntity);
    }

    /**
     * 获取话题信息
     * @param videoTopicFindInfoInVo
     * @param request
     * @return
     */
    @Override
    public R findTopicInfo(VideoTopicFindInfoInVo videoTopicFindInfoInVo, HttpServletRequest request) {
        Long topicId = videoTopicFindInfoInVo.getTopicId();
        if(topicId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"topicId参数无效");
        TopicInfoDetailDto topicInfoDto = videoTopicMapper.queryTopicInfo(topicId);
        if(topicInfoDto == null){
            return R.error(ErrorCode.InfoError.getCode(),"此话题不存在或已删除");
        }
        return R.ok().put("data", topicInfoDto);
    }

    /**
     * 根据话题的标题查询
     * @param videoTopicTitleInfoInVo
     * @param request
     * @return
     */
    @Override
    public R findTopicTitleInfo(VideoTopicTitleInfoInVo videoTopicTitleInfoInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        String topicTitle = videoTopicTitleInfoInVo.getTopicTitle();
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        if(topicTitle == null && topicTitle.equals(""))return R.error(ErrorCode.ParamInvalid.getCode(),"topicTitle参数不能为空");
        Integer page = videoTopicTitleInfoInVo.getPage();
        Integer limit = videoTopicTitleInfoInVo.getLimit();
        Map<String,Object> map = new HashMap<>();
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);
        map.put("topicTitle", topicTitle);
        List<VideoTopicInfoDto> videoTopicInfoDto = videoTopicMapper.queryTopicTitleInfo(map);
        if(videoTopicInfoDto == null){
            return R.error(ErrorCode.InfoError.getCode(),"此话题不存在或已删除");
        }
        int total = videoTopicMapper.topicTitleTotal(topicTitle);
        PageUtils pageUtil = new PageUtils(videoTopicInfoDto, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 新增参与人数
     * @param topicId
     * @param userId
     */
    @Override
    public void updatePartakeNum(Long topicId, Long userId) {
        int partakeNum = videoTopicMapper.queryPartakeNum(topicId);
        Map<String,Object> map = new HashMap<>();
        map.put("topicId", topicId);
        map.put("userId", userId);
        VideoInfoEntity videoByTopic = videoInfoMapper.findVideobyTopicUserId(map);
        if(videoByTopic == null){
            partakeNum ++;
            handleTopicHotNum(topicId, 1);
        }
        //代表新增
        map.put("partakeNum", partakeNum);
        videoTopicMapper.updatePartakeNum(map);
    }

    /**
     * 处理话题热度值
     * 算法：话题热度值=参与人数*2+作品数+转发数*10
     * type  1:参与   2:作品数  3:转发数
     * @param topicId
     * @param type
     */
    @Override
    public void handleTopicHotNum(Long topicId, int type) {
        VideoTopicInfoEntity videoTopic = queryObject(topicId);
        Long partakeNum = videoTopic.getPartakeNum();
        Long shareNum = videoTopic.getShareNum();
        if(videoTopic != null){
            Long hotNum = videoTopic.getHotNum();
            if(type == 1){
                hotNum = hotNum+partakeNum*2;
            }else if(type == 2){
                hotNum += 1;
            }else if(type == 3){
                hotNum = hotNum+shareNum*10;
            }
            videoTopic.setHotNum(hotNum);
            update(videoTopic);
        }
    }

    /**
     * 话题分享
     * @param topicShareNumInVo
     * @param request
     * @return
     */
    @Override
    public R topicShare(TopicShareNumInVo topicShareNumInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long topicId = topicShareNumInVo.getTopicId();
        if(topicId == null)return R.error(ErrorCode.ParamInvalid.getCode(),"topicId参数无效");
        VideoTopicInfoEntity videoTopic = queryObject(topicId);
        if(videoTopic != null){
            Long shareNum = videoTopic.getShareNum();
            shareNum ++;
            videoTopic.setShareNum(shareNum);
            update(videoTopic);
            if(userInfoEntity != null && userInfoEntity.getUserId() != null){
                Long userId = userInfoEntity.getUserId();
                userTopicRelationService.saveRelation(userId, topicId);
            }
            handleTopicHotNum(topicId, 3);
            return R.ok().put("shareNum", shareNum);
        }else{
            return R.error(ErrorCode.ParamInvalid.getCode(),"topicId 参数有误,话题不存在");
        }
    }

    /**
     * 获取话题信息web
     * @param request
     * @return
     */
    @Override
    public R findTopicInfoWeb(HttpServletRequest request) {
        String topicIdStr = request.getParameter("topicId");
        if(topicIdStr == null)return R.error(ErrorCode.ParamInvalid.getCode(),"topicId参数无效");
        Long  topicId = Long.parseLong(topicIdStr);
        TopicInfoDetailDto topicInfoDto = videoTopicMapper .queryTopicInfo(topicId);
        if(topicInfoDto == null){
            return R.error(ErrorCode.InfoError.getCode(),"此话题不存在或已删除");
        }
        return R.ok().put("data", topicInfoDto);
    }
}
