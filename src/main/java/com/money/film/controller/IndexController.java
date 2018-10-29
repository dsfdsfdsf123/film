package com.money.film.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 跟路径以及其他请求
 * @author liugang
 * @create 2018/10/23 22:57
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView root(){
        ModelAndView model = new ModelAndView();
        model.addObject("hello","hello film");
        model.addObject("title","首页");
        model.addObject("mainPage","film/indexFilm");
        model.addObject("mainPageKey","#f");
        model.setViewName("index");
        return model;
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
