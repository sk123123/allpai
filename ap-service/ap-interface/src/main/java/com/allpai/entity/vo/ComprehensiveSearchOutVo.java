package com.allpai.entity.vo;

import com.allpai.entity.dto.UserSearchDto;

import java.util.List;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 13:12
 * 综合信息输出
 */
public class ComprehensiveSearchOutVo {
    //视频
    List<VideoSearchOutVo> queryVideoSearchList;
    //用户
    List<UserSearchDto> queryUserSearchOutVo;
    //话题
    List<TopicSearchOutVo> queryTopicSearchOutVo;

    public List<VideoSearchOutVo> getQueryVideoSearchList() {
        return queryVideoSearchList;
    }
    public void setQueryVideoSearchList(List<VideoSearchOutVo> queryVideoSearchList) {
        this.queryVideoSearchList = queryVideoSearchList;
    }
    public List<UserSearchDto> getQueryUserSearchOutVo() {
        return queryUserSearchOutVo;
    }
    public void setQueryUserSearchOutVo(List<UserSearchDto> queryUserSearchOutVo) {
        this.queryUserSearchOutVo = queryUserSearchOutVo;
    }
    public List<TopicSearchOutVo> getQueryTopicSearchOutVo() {
        return queryTopicSearchOutVo;
    }
    public void setQueryTopicSearchOutVo(List<TopicSearchOutVo> queryTopicSearchOutVo) {
        this.queryTopicSearchOutVo = queryTopicSearchOutVo;
    }
}
