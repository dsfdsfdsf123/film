package com.money.film.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 电影实体
 * @author liugang
 * @create 2018/10/23 22:38
 **/
@Entity
@Table(name = "t_film")
@Data
public class Film {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 200)
    private String name;

    @Column(length = 500)
    private String title;//帖子名称

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;//帖子内容

    @Column(length = 300)
    private String imageName;//电影图片

    private Integer hot;//是否热门 1热门 0不热门

    private Date publishDate;//发布日期
}
