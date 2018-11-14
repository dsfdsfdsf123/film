package com.money.film.controller;

import com.money.film.Service.WebSiteInfoService;
import com.money.film.Service.WebSiteService;
import com.money.film.entity.WebSite;
import com.money.film.entity.WebSiteInfo;
import com.money.film.run.StartupRunner;
import com.money.film.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影动态信息Controller
 * @author liugang
 * @create 2018/10/25 22:56
 **/
@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private StartupRunner startupRunner;

    /**
     * 分页查询电影网址
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(WebSiteInfo webSiteInfo, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(webSiteInfo,page-1,rows);
        Long total = webSiteInfoService.getCount(webSiteInfo);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",webSiteInfoList);
        resultMap.put("total",total);
        return resultMap;
    }

    @RequestMapping("/webSiteInfoList")
    public Map<String,Object> webSiteInfoList(WebSiteInfo webSiteInfo, @RequestParam(value = "page")Integer page, @RequestParam(value = "limit")Integer limit)throws Exception{
        Map<String,Object> map = new HashMap<>();
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(webSiteInfo,page-1,limit);
        Long count = webSiteInfoService.getCount(webSiteInfo);
        map.put("data",webSiteInfoList);
        map.put("count",count);
        map.put("code",0);
        map.put("msg","success");
        return map;
    }

    @RequestMapping("/save")
    public Map<String,Object> save(WebSiteInfo webSiteInfo)throws Exception{
        webSiteInfo.setPublishDate(new Date());
        webSiteInfoService.save(webSiteInfo);
        startupRunner.loadData();
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
                webSiteInfoService.delete(Integer.parseInt(str));
            }
            resultMap.put("success",true);
            startupRunner.loadData();
            return resultMap;
        }else {
            resultMap.put("success",false);
            return resultMap;
        }
    }

    @RequestMapping("/deleteById")
    public Map<String,Object> deleteById(@RequestParam(value = "id")Integer id){
        Map<String,Object> map = new HashMap<>();
        webSiteInfoService.delete(id);
        map.put("success",true);
        return map;
    }


}
