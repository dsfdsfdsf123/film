package com.money.film.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.money.film.Service.WebSiteService;
import com.money.film.entity.Film;
import com.money.film.entity.Link;
import com.money.film.entity.WebSite;
import com.money.film.entity.WebSiteInfo;
import com.money.film.util.PageUtil;
import com.money.film.util.RedisOperator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电影网站控制器
 * @author liugang
 * @create 2018/10/31 22:21
 **/
@Controller
@RequestMapping("/webSite")
public class WebSiteController {

    @Resource
    private WebSiteService webSiteService;

    @Resource
    private RedisOperator redisOperator;

    @RequestMapping("/list/{id}")
    public ModelAndView list(@PathVariable(value = "id",required = false)Integer page) throws Exception{
        ModelAndView mav = new ModelAndView();
        List<WebSite> webSiteList = webSiteService.list(null,page-1,20);
        Long total = webSiteService.getCount(null);
        mav.addObject("webSiteList",webSiteList);
        mav.addObject("pageCode", PageUtil.genPagination("/webSite/list",total,page,20));
        mav.addObject("title","电影网站列表");
        mav.addObject("mainPage","webSite/list");
        mav.addObject("mainPageKey","#f");
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
