package com.money.film.controller;

import com.money.film.Service.WebSiteInfoService;
import com.money.film.entity.WebSite;
import com.money.film.entity.WebSiteInfo;
import com.money.film.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liugang
 * @create 2018/10/31 23:05
 **/
@Controller
@RequestMapping("/webSiteInfo")
public class WebSiteInfoController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @RequestMapping("/list/{id}")
    public ModelAndView mav(@PathVariable(value = "id",required = false)Integer page)throws Exception{
        ModelAndView mav = new ModelAndView();
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(null,page-1,20);
        Long total = webSiteInfoService.getCount(null);
        mav.addObject("webSiteInfoList",webSiteInfoList);
        mav.addObject("pageCode", PageUtil.genPagination("/webSiteInfo/list",total,page,20));
        mav.addObject("title","电影网站动态信息列表");
        mav.addObject("mainPage","webSiteInfo/list");
        mav.addObject("mainPageKey","#f");
        mav.setViewName("index");
        return mav;
    }
}
