package com.allpai.user.service;

import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserLockInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:44
 * 用户锁定信息
 */
public interface UserLockInfoService {
    UserLockInfoEntity queryObject(Long lockId);

    List<UserLockInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserLockInfoEntity userLockInfo);

    void update(UserLockInfoEntity userLockInfo);

    void delete(Long lockId);

    void deleteBatch(Long[] lockIds);

    void saveLoginError(Long userId);

    boolean judgeIsLock(Long userId);
}
