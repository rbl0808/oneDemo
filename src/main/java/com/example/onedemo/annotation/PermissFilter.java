package com.example.onedemo.annotation;

import java.lang.annotation.*;

/**
 * @author zxd
 * @Date 2022/10/17 16:52
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PermissFilter {
    String permissValue();
}
