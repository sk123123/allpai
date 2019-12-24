package com.allpai.common.listener;

import com.allpai.common.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

import com.allpai.entity.user.SysMenuEntity;
import com.allpai.entity.user.UserInfoEntity;
import org.apache.log4j.Logger;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 11:02
 * 在线用户监听
 */
public class OnlineUserListener implements HttpSessionListener{
    private static final Logger logger = Logger.getLogger(OnlineUserListener.class);

    private static Map<Long,HttpSession> cache = new HashMap<Long,HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session= se.getSession();
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(session);
        if(userInfoEntity != null && userInfoEntity.getUserId() != null){
            cache.put(userInfoEntity.getUserId(), session);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session= se.getSession();
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(session);
        if(userInfoEntity != null && userInfoEntity.getUserId() != null){
            cache.remove(userInfoEntity.getUserId());
            logger.info("session销毁:"  + userInfoEntity.getUserId() + "退出");
        }
    }

    public static void addOnline(Long userId,HttpServletRequest request){
        try {
            String sessionId = request.getSession().getId();
            logger.info("缓存数据:" + cache);
            if(cache.containsKey(userId)){
                HttpSession beforeSession = cache.get(userId);
                String beforeSessionId = beforeSession.getId();
                if(!sessionId.equals(beforeSessionId)){
                    logger.info("踢掉的账号:" + userId);
                    beforeSession.invalidate();
                }
            }
            cache.put(userId, request.getSession());
        } catch (Exception e) {
			e.printStackTrace();
        }

    }

    public static void  removeOnline(HttpServletRequest request){
        UserInfoEntity userInfoEntity = SessionUtils.getSessionUser(request);
        if(userInfoEntity != null && userInfoEntity.getUserId() != null)
            cache.remove(userInfoEntity.getUserId());
    }
}
