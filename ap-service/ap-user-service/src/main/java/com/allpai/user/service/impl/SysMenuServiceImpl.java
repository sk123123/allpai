package com.allpai.user.service.impl;

import com.allpai.common.utils.Constant;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.user.mapper.SysMenuMapper;
import com.allpai.user.mapper.SysUserMapper;
import com.allpai.user.service.SysMenuService;
import com.allpai.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/20 0020 11:56
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserService sysUserService;
//    @Autowired
//    private SysRoleMenuService sysRoleMenuService;

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList 用户菜单ID
     */
    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = sysMenuMapper.queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 获取不包含按钮的菜单列表
     */
    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return sysMenuMapper.queryNotButtonList();
    }

    /**
     * 获取用户菜单列表
     */
    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        // 系统管理员，拥有最高权限
        if (userId == 1) {
            return getAllMenuList(null);
        }

        // 用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 查询菜单
     */
    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return sysMenuMapper.queryObject(menuId);
    }

    /**
     * 查询菜单列表
     */
    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return sysMenuMapper.queryList(map);
    }

    /**
     * 查询总数
     */
    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysMenuMapper.queryTotal(map);
    }

    /**
     * 保存菜单
     */
    @Override
    public void save(SysMenuEntity menu) {
        sysMenuMapper.save(menu);
    }

    /**
     * 修改
     */
    @Override
    public void update(SysMenuEntity menu) {
        sysMenuMapper.update(menu);
    }

    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteBatch(Long[] menuIds) {
        sysMenuMapper.deleteBatch(menuIds);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        // 查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        // 递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()) {// 目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
