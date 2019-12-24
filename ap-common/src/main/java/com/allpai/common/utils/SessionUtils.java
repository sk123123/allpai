package com.allpai.common.utils;
import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserInfoEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 10:34
 * session工具类
 */
public class SessionUtils {
    private final static String USERSESSION = "USERSESSION";
    private final static String WEBUSERSESSION = "WEBUSERSESSION";
    public static void  setSessionUser(HttpServletRequest request,UserInfoEntity userInfoEntity){
        request.getSession().setAttribute(USERSESSION, userInfoEntity);
    }

    public static UserInfoEntity getSessionUser(HttpServletRequest request){
        return (UserInfoEntity) request.getSession().getAttribute(USERSESSION);
    }

    public static void  setSessionUser(HttpSession session,UserInfoEntity userInfoEntity){
        session.setAttribute(USERSESSION, userInfoEntity);
    }

    public static UserInfoEntity getSessionUser(HttpSession session){
        return (UserInfoEntity) session.getAttribute(USERSESSION);
    }

    public static void  setSessionShopUser(HttpServletRequest request,UserInfoEntity userInfoEntity){
        request.getSession().setAttribute(WEBUSERSESSION, userInfoEntity);
    }

    public static UserInfoEntity getSessionShopUser(HttpServletRequest request){
        return (UserInfoEntity) request.getSession().getAttribute(WEBUSERSESSION);
    }

    public static void  setSessionShopUser(HttpSession session,UserInfoEntity userInfoEntity){
        session.setAttribute(WEBUSERSESSION, userInfoEntity);
    }

    public static UserInfoEntity getSessionShopUser(HttpSession session){
        return (UserInfoEntity) session.getAttribute(WEBUSERSESSION);
    }
}
