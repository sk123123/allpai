//package com.allpai.common.config;
//
//import org.apache.shiro.authc.AuthenticationToken;
//
///**
// * 自定义 token 对象
// * @author sunkai
// * @version 1.0
// * @date 2019/12/11 0011 10:20
// */
//public class OAuth2Token implements AuthenticationToken {
//    private static final long serialVersionUID = 1L;
//
//    private String token;
//
//    public OAuth2Token(String token){
//        this.token = token;
//    }
//
//    @Override
//    public String getPrincipal() {
//        return token;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return token;
//    }
//}
