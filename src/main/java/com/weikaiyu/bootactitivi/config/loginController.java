package com.weikaiyu.bootactitivi.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

    @RequestMapping("/")
    public String loginredirect(){
        return "redirect:/login-view";
    }

    @RequestMapping("/login-view")
    public String login(){
        return "login";
    }
}
