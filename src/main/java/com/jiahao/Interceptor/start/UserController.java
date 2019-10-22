package com.jiahao.Interceptor.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("Interceptor")
@Controller
public class UserController {

    @RequestMapping("save")
    public void save(){
        System.out.println("UserController的save方法...");
    }

    @RequestMapping("delete")
    public void delete(){
        System.out.println("UserController的delete方法...");
    }
}
