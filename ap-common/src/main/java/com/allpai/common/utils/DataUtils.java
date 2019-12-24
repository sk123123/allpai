package com.allpai.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.utils.Base64Helper;
/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 11:54
 */
public class DataUtils {
    /**
     * 获取验证码
     * @return
     */
    public static String getCode(){
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for(int i = 0 ;i < 6 ; i++){
            code.append(random.nextInt(10));
        }
//		code.append(888888);
        return code.toString();
    }

    /**
     * 进行md5加密
     * @param input
     * @return
     */
    public static String getMd5(String input) {
        byte[] out = (byte[]) null;
        try {
            byte[] btInput = input.getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(btInput);
            out = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return toHexString(out);
    }
    private static String toHexString(byte[] out) {
        StringBuilder buf = new StringBuilder();
        for (byte b : out)
            buf.append(String.format("%02X", new Object[] { Byte.valueOf(b) }));

        return buf.toString();
    }


    /**
     * 获取Token
     * @param uid
     * @return
     */
    public static String getToken(Long uid){
        Long time = System.currentTimeMillis();
        String tokenStr = "" + time + uid;
        try {
            return getEncodedBase64(tokenStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tokenStr;
    }

    public static String getToken(String key){
        Long time = System.currentTimeMillis();
        String tokenStr = "" + time + key;
        try {
            return getEncodedBase64(tokenStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tokenStr;
    }

    /**
     * base64加密方法
     * @param text
     * @return
     */
    public static String getEncodedBase64(String text) throws UnsupportedEncodingException{
        return Base64Helper.encode(text, "utf-8");
    }

    /**
     * base64解密方法
     * @param text
     * @return
     */
    public static String getDecodedBase64(String text) throws UnsupportedEncodingException{
        return Base64Helper.decode(text, "utf-8");
    }

    /**
     * 返回的数据
     * @param response
     * @param
     */
    public static void returnData(HttpServletResponse response,R r){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSON.toJSONString(r));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 获取请求中body数据
     * @param request
     * @return
     * @throws Exception
     */
    public static String getReqBodyData(HttpServletRequest request) {
        BufferedReader reqData = null;
        StringBuilder bodyData = null;
        try {
            reqData = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            bodyData = new StringBuilder();
            String temporaryData;
            while ((temporaryData = reqData.readLine()) != null){
                bodyData.append(temporaryData);
            }
        } catch (Exception e) {
            return null;
        }finally{
            if(reqData != null){
                try {
                    reqData.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bodyData.toString();
    }

    public static String md5_32(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
//		String en = getEncodedBase64("12345678");
//		String de = getDecodedBase64("MTIzNDU2Nzg=");
//		System.out.println(en);
//		System.out.println(de);
//		System.out.println(getMd5("deng1991"));
    }
}
