package com.money.film.entity;

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
@Data
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
}
