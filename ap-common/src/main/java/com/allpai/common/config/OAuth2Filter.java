//package com.allpai.common.config;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author sunkai
// * @version 1.0
// * @date 2019/12/11 0011 10:16
// */
//public class OAuth2Filter extends AuthenticatingFilter {
//    @Override
//    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        // 获取请求token
//        String token = getRequestToken((HttpServletRequest) servletRequest);
//        if(StringUtils.isBlank(token)){
//            return null;
//        }
//        return new OAuth2Token(token);
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        return false;
//    }
//
//    /**
//     * 获取请求的token
//     */
//    private String getRequestToken(HttpServletRequest httpRequest){
//        // 从header中获取token
//        String token = httpRequest.getHeader("token");
//        // 如果header中不存在token，则从参数中获取token
//        if(StringUtils.isBlank(token)){
//            token = httpRequest.getParameter("token");
//        }
//        return token;
//    }
//}
