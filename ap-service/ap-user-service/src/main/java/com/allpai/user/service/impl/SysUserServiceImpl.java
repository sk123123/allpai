package com.allpai.user.service.impl;

import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.SysUserEntity;
import com.allpai.user.mapper.SysUserMapper;
import com.allpai.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/19 0019 17:52
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
//    @Autowired
//    private SysUserRoleService sysUserRoleService;

    /**
     * 查询用户的所有权限
     * @param userId 用户ID
     */
    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserMapper.queryAllPerms(userId);
    }

    /**
     * 查询用户的所有菜单ID
     */
    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    /**
     * 根据用户名，查询系统用户
     */
    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserMapper.queryByUserName(username);
    }

    /**
     * 根据用户ID，查询用户
     * @param userId
     * @return
     */
    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserMapper.queryObject(userId);
    }

    /**
     * 查询用户列表
     */
    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserMapper.queryList(map);
    }

    /**
     * 查询总数
     */
    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserMapper.queryTotal(map);
    }

    /**
     * 保存用户
     */
    @Override
    @Transactional
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());
        // sha256加密
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        sysUserMapper.save(user);

        // 保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    /**
     * 修改用户
     */
    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserMapper.update(user);

        // 保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    /**
     * 删除用户
     */
    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        sysUserMapper.deleteBatch(userId);
    }

    /**
     * 修改密码
     * @param userId 用户ID
     * @param password 原密码
     * @param newPassword 新密码
     */
    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserMapper.updatePassword(map);
    }
}
