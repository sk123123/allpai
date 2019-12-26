package com.allpai.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URL;

/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/26 0026 14:28
 * 根据经纬度去获取城市地址
 */
public class CityUtils {
    public static String getCity(String address){
        if(StringUtils.isBlank(address)) return null;
        String city  = null;
        try {
            String[] addresArray = address.split(",");
            String add;
            add = getAdd(addresArray[0],addresArray[1]);
            JSONObject object = JSONObject.parseObject(add);
            JSONArray jsonArray = object.getJSONArray("addrList");
            JSONObject obj = jsonArray.getJSONObject(0);
            String admName = obj.getString("admName");
            city = admName.split(",")[1];
            return city;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getAdd(String log, String lat ) throws IOException {
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        URL url = new URL(urlString);
        java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
            res += line+"\n";
        }
        in.close();
        return res;
    }

    public static void main(String[] args) {
        //114.128025,22.608011  114.119392,22.610133
        System.out.println(getCity("114.128025,22.608011"));
    }
}
