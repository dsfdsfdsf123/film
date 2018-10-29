package com.money.film.controller;

import com.money.film.Service.FilmService;
import com.money.film.entity.Film;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
    private FilmService filmService;

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
}
