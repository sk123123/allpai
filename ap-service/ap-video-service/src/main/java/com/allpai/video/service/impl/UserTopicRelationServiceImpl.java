package com.allpai.video.service.impl;

import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserTopicRelationEntity;
import com.allpai.video.mapper.UserTopicRelationMapper;
import com.allpai.video.service.UserTopicRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 11:15
 */
@Service("userTopicRelationService")
public class UserTopicRelationServiceImpl implements UserTopicRelationService {
    @Autowired
    private UserTopicRelationMapper userTopicRelationMapper;
    @Override
    public UserTopicRelationEntity queryObject(Long userTopicId) {
        return userTopicRelationMapper.queryObject(userTopicId);
    }

//    @Override
//    public List<UserTopicRelationEntity> queryList(Map<String, Object> map) {
//        return userTopicRelationMapper.queryList(map);
//    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userTopicRelationMapper.queryTotal(map);
    }

    @Override
    public void save(UserTopicRelationEntity userTopicRelation) {
        userTopicRelationMapper.save(userTopicRelation);
    }

    @Override
    public void update(UserTopicRelationEntity userTopicRelation) {
        userTopicRelationMapper.update(userTopicRelation);
    }

    @Override
    public void delete(Long userTopicId) {
        userTopicRelationMapper.delete(userTopicId);
    }

    @Override
    public void deleteBatch(Long[] userTopicIds) {
        userTopicRelationMapper.deleteBatch(userTopicIds);
    }

    /**
     * 用户话题关系保存
     * @param userId
     * @param topicId
     * @return
     */
    @Override
    public void saveRelation(Long userId, Long topicId) {
        UserTopicRelationEntity userTopicRelation = findRelation(userId, topicId);
        if(userTopicRelation == null){
            userTopicRelation = new UserTopicRelationEntity();
            userTopicRelation.setTotalNum(1);
            userTopicRelation.setTopicId(topicId);
            userTopicRelation.setType(1);
            userTopicRelation.setUserId(userId);
            userTopicRelation.setCreateTime(new Date());
            save(userTopicRelation);
        }else{
            int totalNum = userTopicRelation.getTotalNum();
            totalNum ++;
            userTopicRelation.setTotalNum(totalNum);
            update(userTopicRelation);
        }
    }

    /**
     * 用户话题关联查找
     * @param userId
     * @param topicId
     * @return
     */
    @Override
    public UserTopicRelationEntity findRelation(Long userId, Long topicId) {
        Map<String, Object> map = new HashMap<>();
        map.put("offset", 0);
        map.put("limit", 1);
        map.put("userId", userId);
        map.put("topicId", topicId);
        List<UserTopicRelationEntity> list = userTopicRelationMapper.queryList(map);
        if(list.size() > 0 && list.get(0) != null){
            return list.get(0);
        }
        return null;
    }
}
