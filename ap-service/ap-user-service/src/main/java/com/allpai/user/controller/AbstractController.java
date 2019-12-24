package com.allpai.user.controller;

import com.allpai.common.utils.ShiroUtils;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/19 0019 17:57
 * Controller公共组件
 */
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
