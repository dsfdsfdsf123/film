package com.money.film.controller;

import com.money.film.Service.LinkService;
import com.money.film.entity.Link;
import com.money.film.run.StartupRunner;
import com.money.film.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liugang
 * @create 2018/10/25 22:56
 **/
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

    private static final Logger logger = LoggerFactory.getLogger(LinkAdminController.class);

    @Resource
    private LinkService linkService;

    @Resource
    private StartupRunner startupRunner;

    /**
     * 分页查询友情链接
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<Link> linkList = linkService.list(page-1,rows);
        Long total = linkService.getCount();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",linkList);
        resultMap.put("total",total);
        return resultMap;
    }

    @RequestMapping("/linkList")
    public Map<String,Object> linkList(HttpServletRequest request,@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "limit",required = false)Integer limit)throws Exception{
        String linkName = request.getParameter("linkName");
        Map<String,Object> map = new HashMap<>();
        if(StringUtil.isNotEmpty(linkName)){
            List<Link> linkList = linkService.linkList(page-1,limit,linkName);
            Long total = linkService.total(linkName);
            map.put("count",total);
            map.put("data",linkList);
        }else{
            List<Link> linkList = linkService.list(page-1,limit);
            Long total = linkService.getCount();
            map.put("count",total);
            map.put("data",linkList);
        }
        map.put("code",0);
        map.put("msg","接收成功");
        return map;
    }

    /**
     * 添加或者修改
     * @param link
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String,Object> save(Link link)throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        linkService.save(link);
        startupRunner.loadData();
        resultMap.put("success",true);
        return resultMap;
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam(value = "ids")String ids)throws Exception{
        String[] idsStr = ids.split(",");
        for(String str:idsStr){
            linkService.delete(Integer.parseInt(str));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        startupRunner.loadData();
        return map;
    }

    @RequestMapping("/deleteById")
    public Map<String,Object> deleteById(HttpServletRequest request){
        String id = request.getParameter("id");
        linkService.delete(Integer.parseInt(id));
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        startupRunner.loadData();
        return map;
    }


}
