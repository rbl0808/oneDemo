package com.example.onedemo.controller;

import com.example.onedemo.annotation.PermissFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zxd
 * @Date 2022/10/17 16:48
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping(value = "/demoA",method = {RequestMethod.GET,RequestMethod.POST})
    @PermissFilter(permissValue = "no")
    public String ping(){
        return "s";
    }
}
