package com.weikaiyu.bootactitivi.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class textCntroller {

    @RequestMapping("/login-success")
    public String login() {
        return "登录成功";
    }

    @GetMapping("/r/r1")
    public String r1() {
        return "r1111111111111111";
    }
    @RequestMapping("/r/r2")
    public String r2() {
        return "r2222222222222222";
    }
}
