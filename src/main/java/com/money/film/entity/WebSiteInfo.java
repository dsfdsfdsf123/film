package com.money.film.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.money.film.util.CustomDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 网站动态更新
 * @author liugang
 * @create 2018/10/23 22:50
 **/
@Entity
@Table(name = "t_webSiteInfo")
public class WebSiteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film;//电影

    @ManyToOne
    @JoinColumn(name = "webSiteId")
    private WebSite webSite;//网站

    @Column(length = 1000)
    private String info;//信息

    @Column(length = 500)
    private String url;//具体网址

    private Date publishDate;//发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public WebSite getWebSite() {
        return webSite;
    }

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
