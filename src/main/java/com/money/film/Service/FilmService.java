package com.money.film.Service;

import com.money.film.entity.Film;

/**
 * @author liugang
 * @create 2018/10/25 22:02
 **/
public interface FilmService {

    /**
     * 添加电影
     * @param film
     */
    public void save(Film film);
}
