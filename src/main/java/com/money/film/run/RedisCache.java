package com.money.film.run;

import com.alibaba.fastjson.JSON;
import com.money.film.Service.FilmService;
import com.money.film.Service.LinkService;
import com.money.film.Service.WebSiteInfoService;
import com.money.film.Service.WebSiteService;
import com.money.film.entity.Film;
import com.money.film.repository.WebSiteRepository;
import com.money.film.util.RedisOperator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liugang
 * @create 2018/12/1 10:58
 **/
@Component("redisCache")
public class RedisCache implements CommandLineRunner {

    @Resource
    private RedisOperator redisOperator;
    @Resource
    private FilmService filmService;
    @Resource
    private LinkService linkService;
    @Resource
    private WebSiteService webSiteService;
    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 加载数据到redis里面去
     */
    public void loadData(){
        //获取最新10条电影动态信息
        redisOperator.set("newWebSiteInfoList", JSON.toJSONString(webSiteInfoService.list(null,0,10)));
        Film film = new Film();
        film.setHot(1);
        //获取最新10条热门电影
        redisOperator.set("newHotFilmList",JSON.toJSONString(filmService.list(film,0,10)));
        //获取最新32条热门电影+首页显示
        redisOperator.set("newIndexHotFilmList",JSON.toJSONString(filmService.list(film,0,32)));
        //获取最新10条电影网站收录
        redisOperator.set("newWebSiteList",JSON.toJSONString(webSiteService.newWebSiteList(0,10)));
        //获取最新10条电影信息
        redisOperator.set("newFilmList",JSON.toJSONString(filmService.list(null,0,10)));
        //获取所有链接信息
        redisOperator.set("linkList",JSON.toJSONString(linkService.listAll()));
    }

    @Override
    public void run(String... args) throws Exception {
        this.loadData();
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 redisCache <<<<<<<<<<<<<");
    }
}
