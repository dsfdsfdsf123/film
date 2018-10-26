package com.money.film.controller;

import com.money.film.Service.LinkService;
import com.money.film.entity.Link;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private LinkService linkService;

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
        return map;
    }

}
