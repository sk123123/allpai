package com.allpai.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * @author sunkai
 * @version 1.0
 * @date 2019/12/10 0010 11:45
 * Base64加解密
 */
public class AESUtils {
    private static final String IV_STRING = "A-16-Byte-String";
    private static final String charset = "UTF-8";
    private static final String KEY = "fyblkj2018000011";
    /**
     * 加密
     * @param data
     * @return
     */
    public static String encryptData(String data){
        try {
            return aesEncryptString(data, KEY);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 解密
     * @param data
     * @return
     */
    public static String decryptData(String data) {
        try {
            return aesDecryptString(data, KEY);
        } catch (Exception e) {
            return null;
        }

    }

    public static void main(String[] args) {

        String string = AESUtils.encryptData("12345678");
        System.out.println("加密后:" +string);
        System.out.println("秘钥:" +KEY);
        System.out.println("解密后:"+AESUtils.decryptData(string));


    }

    public static String aesEncryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] contentBytes = content.getBytes(charset);
        byte[] keyBytes = key.getBytes(charset);
        byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedBytes);
    }

    public static String aesDecryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Decoder decoder = Base64.getDecoder();
        byte[] encryptedBytes = decoder.decode(content);
        byte[] keyBytes = key.getBytes(charset);
        byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
        return new String(decryptedBytes, charset);
    }

    public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
    }

    private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        byte[] initParam = IV_STRING.getBytes(charset);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKey, ivParameterSpec);

        return cipher.doFinal(contentBytes);
    }
}
