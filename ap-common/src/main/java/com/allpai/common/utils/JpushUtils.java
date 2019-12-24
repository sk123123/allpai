package com.allpai.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/13 0013 13:19
 * 极光推送工具类
 */
public class JpushUtils {
    protected static final String APP_KEY ="916bec5a49f6311e89e01f8f";
    protected static final String MASTER_SECRET = "c872f4df4469ba2cf7c4ed52";
    protected static final Logger LOG = LoggerFactory.getLogger(JpushUtils.class);
    public static void main(String[] args) {
        Map<String,String> extras = new HashMap<String, String>();
        sendPush("160a3797c82a960e4f3", "点赞通知 : @天涯 赞了您的作品《视频标题》，去看看吧~", "凡拍11111",extras);
    }

    public static void sendPush(String registrationId,String content,String title, Map<String, String> extras) {
        if(StringUtils.isBlank(registrationId)) return;
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);

        final PushPayload payload = buildPushObject_android_and_ios(registrationId, content, title,extras);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            System.out.println(result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    public static PushPayload buildPushObject_android_and_ios(String registrationId,String content,String title, Map<String, String> extras) {
        /*Map<String, String> extras = new HashMap<String, String>();
        extras.put("content", content);
        extras.put("type", "1");*/
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                //通知所有
//                .setAudience(Audience.all())
                //通知单个用户
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title)
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .setSound("happy")
                                .addExtras(extras).build())
                        .build())
                .build();
    }
}
