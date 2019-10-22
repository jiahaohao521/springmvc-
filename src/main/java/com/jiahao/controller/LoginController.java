package com.jiahao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@RequestMapping("login")
@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping("login")
    public String login(@RequestParam String name,@RequestParam String password){
        System.out.println("用户名:" + name + " ____ " + "密码：" + password);
        return "success";
    }
}

