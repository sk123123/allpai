package com.allpai.user.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 12:55
 * 用户配置
 */
@Mapper
public interface UserConfigMapper extends BaseMapper<UserConfigEntity> {
    UserConfigEntity queryObjectByUserId(Long userId);
}
