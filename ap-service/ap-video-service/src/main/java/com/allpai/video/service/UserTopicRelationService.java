package com.allpai.video.service;

import com.allpai.entity.user.UserTopicRelationEntity;

import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 11:14
 * 用户和话题关系表
 */
public interface UserTopicRelationService {
    UserTopicRelationEntity queryObject(Long userTopicId);

//    List<UserTopicRelationEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserTopicRelationEntity userTopicRelation);

    void update(UserTopicRelationEntity userTopicRelation);

    void delete(Long userTopicId);

    void deleteBatch(Long[] userTopicIds);

    void saveRelation(Long userId,Long topicId);

    UserTopicRelationEntity findRelation(Long userId, Long topicId);
}
