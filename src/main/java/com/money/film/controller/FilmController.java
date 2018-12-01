package com.money.film.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.money.film.Service.FilmService;
import com.money.film.Service.WebSiteInfoService;
import com.money.film.entity.Film;
import com.money.film.entity.Link;
import com.money.film.entity.WebSite;
import com.money.film.entity.WebSiteInfo;
import com.money.film.util.PageUtil;
import com.money.film.util.RedisOperator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 *  电影controller类
 * @author liugang
 * @create 2018/10/29 23:50
 **/
@Controller
@RequestMapping("/film")
public class FilmController {

    @Resource
    private RedisOperator redisOperator;

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 测试在同一个controller里面跳转
     * @param request
     * @param attributes
     * @return
     */
    @RequestMapping("/test")
    public ModelAndView sendValue(HttpServletRequest request, RedirectAttributes attributes){
        ModelAndView mav = new ModelAndView();
        String water = request.getParameter("water");
        attributes.addAttribute("water",water);
        mav.setViewName("redirect:/film/getValue");
        return mav;
    }

    /**
     * 测试在同一个controller里面跳转
     * @param request
     * @return
     */
    @RequestMapping("/getValue")
    public ModelAndView getValue(HttpServletRequest request){
        String water = request.getParameter("water");
        ModelAndView mav = new ModelAndView("/");
        return mav;
    }

    /**
     * 简单模糊查询
     * @param s_film
     * @param bindingResult
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView search(@Valid Film s_film, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            mav.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            mav.addObject("title","首页");
            mav.addObject("mainPage","film/indexFilm");
            mav.addObject("mainPageKey","#f");
            mav.setViewName("index");
            return mav;
        }else{
            List<Film> filmList = filmService.list(s_film,0,32);
            mav.addObject("title",s_film.getName());
            mav.addObject("mainPage","film/result");
            mav.addObject("mainPageKey","#f");
            mav.addObject("s_film",s_film);
            mav.addObject("total",filmList.size());
            mav.addObject("filmList",filmList);
            mav.setViewName("index");
            return mav;
        }
    }

    /**
     * 分页显示电影信息
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{id}")
    public ModelAndView list(@PathVariable(value = "id",required = false)Integer page)throws Exception{
        ModelAndView mav = new ModelAndView();
        List<Film> filmList =filmService.list(null,page-1,20);
        Long total = filmService.getCount(null);
        mav.addObject("filmList",filmList);
        mav.addObject("pageCode", PageUtil.genPagination("/film/list",total,page,20));
        mav.addObject("title","电影列表");
        mav.addObject("mainPage","film/list");
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

    /**
     * 根据id获取电影详细信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable(value = "id",required = false)Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        Film film = filmService.findById(id);
        mav.addObject("film",film);
        mav.addObject("title",film.getTitle());
        mav.addObject("randomFilmList",filmService.randomList(8));
        mav.addObject("webSiteInfoList",webSiteInfoService.getByFilmId(id));
        mav.addObject("mainPage","film/view");
        mav.addObject("pageCode",this.getUpDownPageCode(filmService.getLast(id),filmService.getNext(id)));
        mav.addObject("mainPageKey","#f");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取下一篇和上一篇电影
     * @param lastFilm
     * @param nextFilm
     * @return
     */
    public String getUpDownPageCode(Film lastFilm,Film nextFilm){
        StringBuffer pageCode = new StringBuffer();
        if (lastFilm==null || lastFilm.getId()==null){
            pageCode.append("<p>上一篇没有了</p>");
        }
        else{
            pageCode.append("<p>上一篇：<a href='/film/"+lastFilm.getId()+"'>"+lastFilm.getTitle()+"</a></p>");
        }
        if (nextFilm == null || nextFilm.getId() == null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='/film/"+nextFilm.getId()+"'>"+nextFilm.getTitle()+"</a></p>");
        }

        return pageCode.toString();
    }
}
