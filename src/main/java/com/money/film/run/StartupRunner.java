package com.money.film.run;

import com.money.film.Service.FilmService;
import com.money.film.Service.LinkService;
import com.money.film.Service.WebSiteInfoService;
import com.money.film.Service.WebSiteService;
import com.money.film.entity.Film;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 缓存数据
 * @author liugang
 * @create 2018/10/28 19:45
 **/
@Component("startupRunner")
public class StartupRunner implements CommandLineRunner , ServletContextListener {

    private ServletContext application = null;

    @Resource
    private FilmService filmService;

    @Resource
    private LinkService linkService;

    @Resource
    private WebSiteService webSiteService;

    @Resource
    private WebSiteInfoService webSiteInfoService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 StartupRunner <<<<<<<<<<<<<");
        this.loadData();
    }

    /**
     * 加载数据到application缓存中
     */
    public void loadData(){
        //获取最新10条电影动态信息
        application.setAttribute("newWebSiteInfoList",webSiteInfoService.list(null,0,10));
        Film film = new Film();
        film.setHot(1);
        //获取最新10条热门电影
        application.setAttribute("newHotFilmList",filmService.list(film,0,10));
        //获取最新32条热门电影+首页显示
        application.setAttribute("newIndexHotFilmList",filmService.list(film,0,32));
        //获取最新10条电影网站收录
        application.setAttribute("newWebSiteList",webSiteService.newWebSiteList(0,10));
        //获取最新10条电影信息
        application.setAttribute("newFilmList",filmService.list(null,0,10));
        //获取所有链接信息
        application.setAttribute("linkList",linkService.listAll());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        application = servletContextEvent.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
