package com.money.film.Service;

import com.money.film.entity.WebSiteInfo;

import java.util.List;

/**
 * 电影动态信息service接口
 * @author liugang
 * @create 2018/10/25 22:40
 **/
public interface WebSiteInfoService {

    /**
     * 分页查询电影动态信息
     * @param page
     * @param pageSize
     * @return
     */
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     * @return
     */
    public Long getCount(WebSiteInfo webSiteInfo);

    /**
     * 根据电影id查询动态信息
     * @param filmId
     * @return
     */
    public List<WebSiteInfo> getByFilmId(Integer filmId);

    /**
     * 根据电影网址id查询电影动态信息
     * @param webSiteId
     * @return
     */
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId);

    /**
     * 保存webSiteInfo
     * @param webSiteInfo
     */
    public void save(WebSiteInfo webSiteInfo);

    /**
     * 根据id删除websiteInfo
     * @param id
     */
    public void delete(Integer id);

}
