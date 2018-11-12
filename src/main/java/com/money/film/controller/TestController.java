package com.money.film.controller;

import com.alibaba.fastjson.JSON;
import com.money.film.entity.WebSite;
import com.money.film.repository.WebSiteRepository;
import com.money.film.util.RedisOperator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liugang
 * @create 2018/11/11 22:58
 **/
@RestController
@RequestMapping("/testRedis")
public class TestController {

    @Resource
    private RedisOperator redisOperator;
    @Resource
    private WebSiteRepository webSiteRepository;

    @RequestMapping("/set")
    public String set(){
        redisOperator.set("name","杨武");
        redisOperator.incr("age",22);
        WebSite webSite = new WebSite();
        webSite.setId(5);
        List<WebSite> list = webSiteRepository.findAll();
        System.out.println(list);
        redisOperator.set("json:list", JSON.toJSONString(list));
        return "success";
    }

    @RequestMapping("/get")
    public String get(){
        return redisOperator.get("json:list");
    }

}
