package com.allpai.user.service;

import com.allpai.entity.user.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/20 0020 11:53
 * 菜单管理
 */
public interface SysMenuService {
    //根据父菜单，查询子菜单
    List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    //获取不包含按钮的菜单列表
    List<SysMenuEntity> queryNotButtonList();

    //获取用户菜单列表
    List<SysMenuEntity> getUserMenuList(Long userId);

    //查询菜单
    SysMenuEntity queryObject(Long menuId);

    //查询菜单列表
    List<SysMenuEntity> queryList(Map<String, Object> map);

   //查询总数
    int queryTotal(Map<String, Object> map);

    //保存菜单
    void save(SysMenuEntity menu);

    //修改
    void update(SysMenuEntity menu);

    //删除
    void deleteBatch(Long[] menuIds);
}
