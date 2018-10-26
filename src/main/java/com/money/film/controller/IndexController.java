package com.money.film.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跟路径以及其他请求
 * @author liugang
 * @create 2018/10/23 22:57
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String root(Model model){
        model.addAttribute("hello","hello film");
        model.addAttribute("title","首页");
        return "index";
    }

    /**
     * 登录请求
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/admin")
    public String toAdmin(){
        return "/admin/main";
    }
}
