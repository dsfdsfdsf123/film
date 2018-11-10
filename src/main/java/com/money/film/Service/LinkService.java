package com.money.film.Service;

import com.money.film.entity.Link;

import java.util.List;

/**
 * 友情连接service接口
 * @author liugang
 * @create 2018/10/25 22:40
 **/
public interface LinkService {

    /**
     * 分页查询友情链接
     * @param page
     * @param pageSize
     * @return
     */
    public List<Link> list (Integer page,Integer pageSize);

    /**
     * 查询所有友情链接
     * @return
     */
    public List<Link> listAll();

    /**
     * 获取总记录数
     * @return
     */
    public Long getCount();

    /**
     * 添加或者修改友情链接
     * @param link
     */
    public void save(Link link);

    /**
     * 根据id删除友情链接
     * @param id
     */
    public void delete(Integer id);

    /**
     * 根据链接名称获取list
     * @param page
     * @param pageSize
     * @param linkName
     * @return
     */
    public List<Link> linkList(Integer page, Integer pageSize,String linkName);

    /**
     * 获取总数
     * @param linkName
     * @return
     */
    public Long total(String linkName);
}
