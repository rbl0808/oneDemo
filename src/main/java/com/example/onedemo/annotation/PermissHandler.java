package com.example.onedemo.annotation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zxd
 * @Date 2022/10/17 16:54
 */
@Component
public class PermissHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            PermissFilter permissFilter = method.getMethodAnnotation(PermissFilter.class);
            if (null != permissFilter) {
                if (StringUtils.equals(permissFilter.permissValue(), "access")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

}
