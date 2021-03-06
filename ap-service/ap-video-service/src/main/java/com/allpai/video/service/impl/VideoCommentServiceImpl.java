package com.allpai.video.service.impl;

import com.allpai.common.constant.HotNumType;
import com.allpai.common.exception.ErrorCode;
import com.allpai.common.utils.CheckTextUtils;
import com.allpai.common.utils.PageUtils;
import com.allpai.common.utils.R;
import com.allpai.common.utils.SessionUtils;
import com.allpai.entity.user.UserInfoEntity;
import com.allpai.entity.video.VideoCommentEntity;
import com.allpai.entity.user.vo.UserCommentDeleteInVo;
import com.allpai.entity.user.vo.UserCommentListInVo;
import com.allpai.entity.video.dto.VCommentSonDto;
import com.allpai.entity.video.dto.VideoCommentDto;
import com.allpai.entity.video.vo.VideoCommentAddInVo;
import com.allpai.entity.video.vo.VideoCommentListInVo;
import com.allpai.video.feignClient.UserFeignClient;
import com.allpai.video.mapper.VideoCommentMapper;
import com.allpai.video.service.VideoCommentService;
import com.allpai.video.service.VideoInfoService;
import org.apache.commons.lang.StringUtils;
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
 * @date 2019/12/13 0013 12:28
 * 短视频评论
 */
@Service("videoCommentService")
public class VideoCommentServiceImpl implements VideoCommentService{
    @Autowired
    private VideoCommentMapper videoCommentMapper;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private VideoInfoService videoInfoService;

    @Override
    public VideoCommentEntity queryObject(Long commentId) {
        return videoCommentMapper.queryObject(commentId);
    }

    @Override
    public List<VideoCommentEntity> queryList(Map<String, Object> map) {
        return videoCommentMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return videoCommentMapper.queryTotal(map);
    }

    @Override
    public void save(VideoCommentEntity videoComment) {
        videoCommentMapper.save(videoComment);
    }

    @Override
    public void update(VideoCommentEntity videoComment) {
        videoCommentMapper.update(videoComment);
    }

    @Override
    public void delete(Long commentId) {
        videoCommentMapper.delete(commentId);
    }

    @Override
    public void deleteBatch(Long[] commentIds) {
        videoCommentMapper.deleteBatch(commentIds);
    }

    /**
     * 新增短视频评论
     * @param videoCommentAddInVo
     * @param request
     * @return
     */
    @Override
    public R add(VideoCommentAddInVo videoCommentAddInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Long userId = userInfoEntity.getUserId();
        if(userId == null)return R.error(ErrorCode.TokenInvalid);
        //检查是否有发布评论权限
        Map<String, Object> map = userFeignClient.blacklistPermission(videoCommentAddInVo, request);
        boolean falg = (boolean)map.get("falg");
        if(!falg)return R.error(ErrorCode.InfoError.getCode(),(String)map.get("msg"));
        //检查内容是否健康
        String content = videoCommentAddInVo.getContent();
        if(!CheckTextUtils.checkData(content))return R.error(ErrorCode.InfoError.getCode(),"评论失败，请您文明用语");

        if(StringUtils.isBlank(videoCommentAddInVo.getContent())) return R.error(ErrorCode.ParamInvalid.getCode(),"content参数不能空");
        userInfoEntity = SessionUtils.getSessionUser(request);
        VideoCommentEntity commentEntity = new VideoCommentEntity();
        commentEntity.setUserId(userInfoEntity.getUserId());
        if(content.length() >200) {
            return R.error(ErrorCode.InfoError.getCode(),"您的输入已超出限制，请重新输入！");
        }else {
            commentEntity.setContent(videoCommentAddInVo.getContent());
        }
        commentEntity.setCreateTime(new Date());
        commentEntity.setParentId(videoCommentAddInVo.getParentId());
        commentEntity.setVideoId(videoCommentAddInVo.getVideoId());
        commentEntity.setGoodNum(0L);
        commentEntity.setToUserId(videoCommentAddInVo.getToUserId());
        commentEntity.setCreateTime(new Date());
        videoCommentMapper.save(commentEntity);
        //评论通知
        userFeignClient.commentNotifiy(commentEntity.getCommentId(), userInfoEntity.getNickName());
        //更新热度值
        videoInfoService.updateVideoHotnum(HotNumType.COMMENT.getCode(), videoCommentAddInVo.getVideoId());
        return R.ok().put("commentId", commentEntity.getCommentId());
    }

    /**
     * 评论列表
     * @param videoCommentListInVo
     * @param request
     * @return
     */
    @Override
    public R list(VideoCommentListInVo videoCommentListInVo, HttpServletRequest request) {
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        Integer page = videoCommentListInVo.getPage();
        Integer limit = videoCommentListInVo.getLimit();
        Long parentId = videoCommentListInVo.getParentId();
        Long userId = userInfoEntity.getUserId();
        if(parentId == 0){
            Map<String, Object> parentMap = new HashMap<>();
            parentMap.put("offset", (page - 1) * limit);
            parentMap.put("limit", limit);
            parentMap.put("videoId", videoCommentListInVo.getVideoId());
            parentMap.put("parentId", parentId);
            parentMap.put("userId", userId);
            List<VideoCommentDto> list = videoCommentMapper.queryParentList(parentMap);
            int total = videoCommentMapper.queryParentTotal(parentMap);
            Map<String, Object> sonMap = new HashMap<>();
            sonMap.put("offset", 0);
            sonMap.put("limit", 3);

            for(VideoCommentDto videoCommentDto : list){
                List<VCommentSonDto> sonList = null;
                sonMap.put("videoId", videoCommentListInVo.getVideoId());
                sonMap.put("parentId", videoCommentDto.getCommentId());
                sonList = videoCommentMapper.querySonList(sonMap);
                int sonNum = videoCommentMapper.querySonTotal(sonMap);
                videoCommentDto.setSonList(sonList);
                videoCommentDto.setSonNum(sonNum);
            }
            PageUtils pageUtil = new PageUtils(list, total, limit, page);
            return R.ok().put("page", pageUtil);
        }else{
            Map<String, Object> map = new HashMap<>();
            map.put("offset", (page - 1) * limit);
            map.put("limit", limit);
            map.put("videoId", videoCommentListInVo.getVideoId());
            map.put("parentId", parentId);
            List<VCommentSonDto> list = videoCommentMapper.querySonList(map);
            int total = videoCommentMapper.querySonTotal(map);
            PageUtils pageUtil = new PageUtils(list, total, limit, page);
            return R.ok().put("page", pageUtil);
        }
    }

    @Override
    public void updateGoodInfo(Long commentId, Long type) {

    }

    @Override
    public R delete(UserCommentDeleteInVo userCommentDeleteInVo) {
        return null;
    }

    @Override
    public void deleteByVideoIds(Long[] videoIds) {

    }

    @Override
    public R userCommentList(UserCommentListInVo userCommentListInVo, HttpServletRequest request) {
        return null;
    }
}
