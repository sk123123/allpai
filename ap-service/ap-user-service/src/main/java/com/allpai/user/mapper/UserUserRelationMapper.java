package com.allpai.user.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.UserUserRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 15:17
 * 用户与用户关系表
 */
@Mapper
public interface UserUserRelationMapper extends BaseMapper<UserUserRelationEntity>{
    int deleteRelation(Map<String, Object> map);

    Long queryuserUserId(@Param("toUserId") Long toUserId, @Param("userId") Long userId, @Param("type") int type);

    boolean findUpdateFlag(Map<String, Object> map);
}
