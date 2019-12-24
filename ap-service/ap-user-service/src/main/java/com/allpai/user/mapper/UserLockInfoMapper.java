package com.allpai.user.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserLockInfoEntity;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:43
 * 用户锁定信息
 */
public interface UserLockInfoMapper extends BaseMapper<UserLockInfoEntity>{
    UserLockInfoEntity queryObjectByUserId(Long userId);
}
