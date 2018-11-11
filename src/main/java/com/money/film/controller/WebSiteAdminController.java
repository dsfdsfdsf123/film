package com.money.film.controller;

import com.money.film.Service.WebSiteInfoService;
import com.money.film.Service.WebSiteService;
import com.money.film.entity.WebSite;
import com.money.film.run.StartupRunner;
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
    public Map<String,Object> list(WebSite webSite, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<WebSite> webSiteList = webSiteService.list(webSite,page-1,rows);
        Long total = webSiteService.getCount(webSite);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",webSiteList);
        resultMap.put("total",total);
        return resultMap;
    }

    @RequestMapping("/webSiteList")
    public Map<String,Object> webSiteList(WebSite webSite,@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "limit",required = false)Integer limit)throws Exception{
        List<WebSite> webSiteList = webSiteService.list(webSite,page-1,limit);
        Long total = webSiteService.getCount(webSite);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",webSiteList);
        resultMap.put("count",total);
        resultMap.put("code",0);
        resultMap.put("msg","查询数据成功");
        return resultMap;
    }

    @RequestMapping("/save")
    public Map<String,Object> save(WebSite webSite)throws Exception{
        webSiteService.save(webSite);
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
            boolean flag = true;
            for(String str:idsStr){
                Integer webSiteId = Integer.parseInt(str);
                if (webSiteInfoService.getByWebSiteId(webSiteId).size()>0){
                    flag = false;
                }else{
                    webSiteService.delete(webSiteId);
                }
            }
            if (flag){
                startupRunner.loadData();
                resultMap.put("success",true);
            }else{
                resultMap.put("success",false);
                resultMap.put("errorInfo","电影动态信息中存在电影信息，不能删除");
            }
            return resultMap;
        }else {
            resultMap.put("success",false);
            return resultMap;
        }
    }

    /**
     * 下拉框
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<WebSite> comboList(String q)throws Exception{
        if (!StringUtil.isNotEmpty(q)){
            return null;
        }else{
            WebSite webSite = new WebSite();
            webSite.setUrl(q);
            return webSiteService.list(webSite,0,30);
        }
    }

    @RequestMapping("/deleteById")
    public Map<String,Object> deleteById(@RequestParam(value = "id")Integer id){
        webSiteService.delete(id);
        Map<String,Object> map =new HashMap<>();
        map.put("success",true);
        return map;
    }


}
