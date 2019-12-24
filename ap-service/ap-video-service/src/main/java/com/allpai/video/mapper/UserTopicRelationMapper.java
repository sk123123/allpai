package com.allpai.video.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserTopicRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 11:13
 * 用户和话题关系表
 */
@Mapper
public interface UserTopicRelationMapper extends BaseMapper<UserTopicRelationEntity> {
}
