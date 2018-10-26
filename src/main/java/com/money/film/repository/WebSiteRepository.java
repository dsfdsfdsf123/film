package com.money.film.repository;

import com.money.film.entity.Link;
import com.money.film.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 收录电影网址Repository接口
 * @author liugang
 * @create 2018/10/25 22:39
 **/
public interface WebSiteRepository extends JpaRepository<WebSite,Integer>, JpaSpecificationExecutor {
}
