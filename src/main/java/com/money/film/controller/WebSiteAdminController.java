package com.money.film.controller;

import com.money.film.Service.LinkService;
import com.money.film.Service.WebSiteService;
import com.money.film.entity.Link;
import com.money.film.entity.WebSite;
import com.money.film.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收录电影网址Controller
 * @author liugang
 * @create 2018/10/25 22:56
 **/
@RestController
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {

    @Resource
    private WebSiteService webSiteService;

    /**
     * 分页查询电影网址
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(WebSite webSite, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<WebSite> webSiteList = webSiteService.list(webSite,page-1,rows);
        Long total = webSiteService.getCount(webSite);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",webSiteList);
        resultMap.put("total",total);
        return resultMap;
    }

    @RequestMapping("/save")
    public Map<String,Object> save(WebSite webSite)throws Exception{
        webSiteService.save(webSite);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam(value = "ids")String ids)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        if (StringUtil.isNotEmpty(ids)){
            String[] idsStr = ids.split(",");
            for(String str:idsStr){
                webSiteService.delete(Integer.parseInt(str));
            }
            resultMap.put("success",true);
            return resultMap;
        }else {
            resultMap.put("success",false);
            return resultMap;
        }
    }


}
