package com.money.film.repository;

import com.money.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 电影Repository
 * @author liugang
 * @create 2018/10/25 22:00
 **/
public interface FileRepository extends JpaRepository<Film,Integer> {
}
