package com.money.film.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.money.film.entity.WebSite;
import com.money.film.repository.WebSiteRepository;
import com.money.film.util.RedisOperator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<WebSite> list = webSiteRepository.findAll();
        redisOperator.set("json:list", JSON.toJSONString(list));
        return "success";
    }

    @RequestMapping("/get")
    public Map<String,Object> get(){
        Map<String,Object> map = new HashMap<>();
        String webSiteListStr = redisOperator.get("json:list");;
        JSONArray list = JSONObject.parseArray(webSiteListStr);
        List<WebSite> webSiteList = (List<WebSite>) JSON.toJSON(list);
        map.put("webSiteList",webSiteList);
        return map;
    }

}
