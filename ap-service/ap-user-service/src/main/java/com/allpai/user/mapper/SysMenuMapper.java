package com.allpai.user.mapper;

import com.allpai.common.mapper.BaseMapper;
import com.allpai.entity.user.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/20 0020 11:52
 * 菜单管理
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();
}
