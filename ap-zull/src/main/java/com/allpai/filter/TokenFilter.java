//package com.allpai.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author sunkai
// * @version 1.0
// * @date 2019/12/20 0020 13:28
// */
//@Component
//public class TokenFilter extends ZuulFilter {
//    private Logger logger = LoggerFactory.getLogger(TokenFilter.class);
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        logger.info("*****************TokenFilter run start*****************");
//        // 获取request对象
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//
//        // 这些是对请求头的匹配，网上有很多解释
//        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.setHeader("Access-Control-Allow-Methods","GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH");
//        response.setHeader("Access-Control-Allow-Headers","authorization, content-type");
//        response.setHeader("Access-Control-Expose-Headers","X-forwared-port, X-forwarded-host");
//        response.setHeader("Vary","Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
//
//        // 跨域请求一共会进行两次请求 先发送options 是否可以请求
//        // 调试可发现一共拦截两次 第一次请求为options，第二次为正常的请求 eg：get请求
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
//            ctx.setSendZuulResponse(false); //验证请求不进行路由
//            ctx.setResponseStatusCode(HttpStatus.OK.value());//返回验证成功的状态码
//            ctx.set("isSuccess", true);
//            return null;
//        }
//        // 第二次请求（非验证，eg：get请求不会进到上面的if） 会走到这里往下进行
//        // 不需要token认证
//        ctx.setSendZuulResponse(true); //对请求进行路由
//        ctx.setResponseStatusCode(HttpStatus.OK.value());
//        ctx.set("isSuccess", true);
//        logger.info("*****************TokenFilter run end*****************");
//        return null;
//    }
//}
