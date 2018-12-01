package com.money.film.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.money.film.entity.Film;
import com.money.film.entity.Link;
import com.money.film.entity.WebSite;
import com.money.film.entity.WebSiteInfo;
import com.money.film.util.RedisOperator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 跟路径以及其他请求
 * @author liugang
 * @create 2018/10/23 22:57
 **/
@Controller
public class IndexController {

    @Resource
    private RedisOperator redisOperator;

    @RequestMapping("/")
    public ModelAndView root(){
        ModelAndView model = new ModelAndView();
        model.addObject("hello","hello film");
        model.addObject("title","首页");
        model.addObject("mainPage","film/indexFilm");
        model.addObject("mainPageKey","#f");
        JSONArray list1 = JSONObject.parseArray(redisOperator.get("newWebSiteInfoList"));
        List<WebSiteInfo> newWebSiteInfoList = (List<WebSiteInfo>) JSON.toJSON(list1);
        JSONArray list2 = JSONObject.parseArray(redisOperator.get("newHotFilmList"));
        List<Film> newHotFilmList = (List<Film>) JSON.toJSON(list2);
        JSONArray list3 = JSONObject.parseArray(redisOperator.get("newIndexHotFilmList"));
        List<Film> newIndexHotFilmList = (List<Film>) JSON.toJSON(list3);
        JSONArray list4 = JSONObject.parseArray(redisOperator.get("newWebSiteList"));
        List<WebSite> newWebSiteList = (List<WebSite>) JSON.toJSON(list4);
        JSONArray list5 = JSONObject.parseArray(redisOperator.get("newFilmList"));
        List<Film> newFilmList = (List<Film>) JSON.toJSON(list5);
        JSONArray list6 = JSONObject.parseArray(redisOperator.get("linkList"));
        List<Link> linkList = (List<Link>) JSON.toJSON(list6);
        model.addObject("newWebSiteInfoList",newWebSiteInfoList);
        model.addObject("newHotFilmList",newHotFilmList);
        model.addObject("newIndexHotFilmList",newIndexHotFilmList);
        model.addObject("newWebSiteList",newWebSiteList);
        model.addObject("newFilmList",newFilmList);
        model.addObject("linkList",linkList);
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
        return "/admin/main1";
    }

    @RequestMapping("aboutMe")
    public ModelAndView aboutMe(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("title","关于本站");
        mav.addObject("mainPage","/common/aboutMe");
        mav.addObject("mainPageKey","#a");
        JSONArray list1 = JSONObject.parseArray(redisOperator.get("newWebSiteInfoList"));
        List<WebSiteInfo> newWebSiteInfoList = (List<WebSiteInfo>) JSON.toJSON(list1);
        JSONArray list2 = JSONObject.parseArray(redisOperator.get("newHotFilmList"));
        List<Film> newHotFilmList = (List<Film>) JSON.toJSON(list2);
        JSONArray list3 = JSONObject.parseArray(redisOperator.get("newIndexHotFilmList"));
        List<Film> newIndexHotFilmList = (List<Film>) JSON.toJSON(list3);
        JSONArray list4 = JSONObject.parseArray(redisOperator.get("newWebSiteList"));
        List<WebSite> newWebSiteList = (List<WebSite>) JSON.toJSON(list4);
        JSONArray list5 = JSONObject.parseArray(redisOperator.get("newFilmList"));
        List<Film> newFilmList = (List<Film>) JSON.toJSON(list5);
        JSONArray list6 = JSONObject.parseArray(redisOperator.get("linkList"));
        List<Link> linkList = (List<Link>) JSON.toJSON(list6);
        mav.addObject("newWebSiteInfoList",newWebSiteInfoList);
        mav.addObject("newHotFilmList",newHotFilmList);
        mav.addObject("newIndexHotFilmList",newIndexHotFilmList);
        mav.addObject("newWebSiteList",newWebSiteList);
        mav.addObject("newFilmList",newFilmList);
        mav.addObject("linkList",linkList);
        mav.setViewName("index");
        return mav;
    }
}
