package com.allpai.user.service.impl;

import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserLockInfoEntity;
import com.allpai.user.mapper.UserLockInfoMapper;
import com.allpai.user.service.UserLockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:44
 */
@Service("userLockInfoService")
public class UserLockInfoServiceImpl implements UserLockInfoService {
    @Autowired
    private UserLockInfoMapper userLockInfoMapper;
    @Override
    public UserLockInfoEntity queryObject(Long lockId) {
        return userLockInfoMapper.queryObject(lockId);
    }

    @Override
    public List<UserLockInfoEntity> queryList(Map<String, Object> map) {
        return userLockInfoMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userLockInfoMapper.queryTotal(map);
    }

    @Override
    public void save(UserLockInfoEntity userLockInfo) {
        userLockInfoMapper.save(userLockInfo);
    }

    @Override
    public void update(UserLockInfoEntity userLockInfo) {
        userLockInfoMapper.update(userLockInfo);
    }

    @Override
    public void delete(Long lockId) {
        userLockInfoMapper.delete(lockId);
    }

    @Override
    public void deleteBatch(Long[] lockIds) {
        userLockInfoMapper.deleteBatch(lockIds);
    }

    @Override
    public void saveLoginError(Long userId) {
        //记到账号锁定表
        UserLockInfoEntity userLockInfo = new UserLockInfoEntity();
        userLockInfo.setUserId(userId);
        userLockInfo.setCreateTime(new Date());
        save(userLockInfo);
    }

    @Override
    public boolean judgeIsLock(Long userId) {
        UserLockInfoEntity userLockInfoEntity = userLockInfoMapper.queryObjectByUserId(userId);
        if(userLockInfoEntity != null){
            Date createTime = userLockInfoEntity.getCreateTime();
            Long hourTime = ((new Date()).getTime() - createTime.getTime())/1000/60/60;
            if(hourTime < 20){
                return true;
            }else{
                delete(userLockInfoEntity.getLockId());
            }
        }
        return false;
    }
}
