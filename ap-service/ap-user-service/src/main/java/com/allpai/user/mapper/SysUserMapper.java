package com.allpai.user.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/19 0019 17:50
 * 系统用户
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     * 查询用户的所有权限
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);
}
