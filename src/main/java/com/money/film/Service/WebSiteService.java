package com.money.film.Service;

import com.money.film.entity.Link;
import com.money.film.entity.WebSite;

import java.util.List;

/**
 * 收录电影网址service接口
 * @author liugang
 * @create 2018/10/25 22:40
 **/
public interface WebSiteService {

    /**
     * 分页查询收录电影网址
     * @param page
     * @param pageSize
     * @return
     */
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     * @return
     */
    public Long getCount(WebSite webSite);

}
