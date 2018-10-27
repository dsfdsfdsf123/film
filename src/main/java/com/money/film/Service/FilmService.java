package com.money.film.Service;

import com.money.film.entity.Film;

import java.util.List;

/**
 * 电影信息
 * @author liugang
 * @create 2018/10/25 22:02
 **/
public interface FilmService {

    /**
     * 添加电影
     * @param film
     */
    public void save(Film film);

    /**
     * 分页查询电影信息
     * @param page
     * @param pageSize
     * @return
     */
    public List<Film> list(Film film, Integer page, Integer pageSize);

    /**
     * 获取总记录数
     * @return
     */
    public Long getCount(Film film);
}
