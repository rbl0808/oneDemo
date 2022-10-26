package com.example.onedemo.config;

import com.example.onedemo.annotation.PermissHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zxd
 * @Date 2022/10/17 16:58
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private PermissHandler permissHandler;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissHandler);
    }
}
