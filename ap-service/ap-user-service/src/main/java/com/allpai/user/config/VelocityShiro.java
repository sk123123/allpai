package com.allpai.user.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/20 0020 12:30
 * Shiro权限标签(Velocity版)
 */
public class VelocityShiro {
    /**
     * 是否拥有该权限
     * @param permission 权限标识
     * @return true：是 false：否
     */
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }
}
