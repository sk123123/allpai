package com.allpai.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/12 0012 23:10
 * 检查文本是否符合要求
 */
public class CheckTextUtils {
    public static boolean checkData(String content){
        try {
            //accessKeyId、accessKeySecret
            IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAItU3DHHqT7dVH", "mI79TzP0fMyZLZPQXh1mYsK0hGUY9u");
            DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
            IAcsClient client = new DefaultAcsClient(profile);
            TextScanRequest textScanRequest = new TextScanRequest();
            textScanRequest.setAcceptFormat(FormatType.JSON); // 指定api返回格式
            textScanRequest.setHttpContentType(FormatType.JSON);
            textScanRequest.setMethod(com.aliyuncs.http.MethodType.POST); // 指定请求方法
            textScanRequest.setEncoding("UTF-8");
            textScanRequest.setRegionId("cn-shanghai");
            List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
            Map<String, Object> task1 = new LinkedHashMap<String, Object>();
            task1.put("dataId", UUID.randomUUID().toString());
            task1.put("content", content);
            tasks.add(task1);
            JSONObject data = new JSONObject();
            /**
             * 文本垃圾检测：antispam
             * 关键词检测：keyword
             **/
            data.put("scenes", Arrays.asList("antispam"));
            data.put("tasks", tasks);
//	        System.out.println(JSON.toJSONString(data, true));
            textScanRequest.setHttpContent(data.toJSONString().getBytes("UTF-8"), "UTF-8", FormatType.JSON);
            // 请务必设置超时时间
            textScanRequest.setConnectTimeout(3000);
            textScanRequest.setReadTimeout(6000);

            HttpResponse httpResponse = client.doAction(textScanRequest);
            if(httpResponse.isSuccess()){
                JSONObject scrResponse = JSON.parseObject(new String(httpResponse.getHttpContent(), "UTF-8"));
//	                System.out.println(JSON.toJSONString(scrResponse, true));
                if (200 == scrResponse.getInteger("code")) {
                    JSONArray taskResults = scrResponse.getJSONArray("data");
                    for (Object taskResult : taskResults) {
                        if(200 == ((JSONObject)taskResult).getInteger("code")){
                            JSONArray sceneResults = ((JSONObject)taskResult).getJSONArray("results");
                            for (Object sceneResult : sceneResults) {
                                String scene = ((JSONObject)sceneResult).getString("scene");
                                String suggestion = ((JSONObject)sceneResult).getString("suggestion");
                                //根据scene和suggetion做相关处理
                                //do something
	                               /* System.out.println("args = [" + scene + "]");
	                                System.out.println("args = [" + suggestion + "]");*/
                                if(scene.equals("antispam") && suggestion.equals("pass")){
                                    return true;
                                }
                            }
                        }else{
                            System.out.println("阿里内容安全检查:task process fail:" + ((JSONObject)taskResult).getInteger("code"));
                        }
                    }
                } else {
                    System.out.println("阿里内容安全检查:detect not success. code:" + scrResponse.getInteger("code"));
                }
            }else{
                System.out.println("阿里内容安全检查:response not success. status:" + httpResponse.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(CheckTextUtils.checkData("真不错的视频法轮功...."));
    }

}
