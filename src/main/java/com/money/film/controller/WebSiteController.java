package com.money.film.controller;

import com.money.film.Service.WebSiteService;
import com.money.film.entity.WebSite;
import com.money.film.util.PageUtil;
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
       mav.setViewName("index");
       return mav;
    }


}
