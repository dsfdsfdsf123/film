package com.money.film.repository;

import com.money.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 电影Repository
 * @author liugang
 * @create 2018/10/25 22:00
 **/
public interface FilmRepository extends JpaRepository<Film,Integer>, JpaSpecificationExecutor {
}
