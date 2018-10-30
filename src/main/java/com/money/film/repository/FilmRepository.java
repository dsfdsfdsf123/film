package com.money.film.repository;

import com.money.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 电影Repository
 * @author liugang
 * @create 2018/10/25 22:00
 **/
public interface FilmRepository extends JpaRepository<Film,Integer>, JpaSpecificationExecutor {

    /**
     * 获取上一篇
     * @param id
     * @return
     */
    @Query(value = "select * from t_film where id<?1 order by id desc limit 1",nativeQuery = true)
    public Film getLast(Integer id);

    @Query(value = "select * from t_film where id>?1 order by id asc limit 1",nativeQuery = true)
    public Film getNext(Integer id);
}
