package com.money.film.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.money.film.util.CustomDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 电影实体
 * @author liugang
 * @create 2018/10/23 22:38
 **/
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Entity
@Table(name = "t_film")
public class Film {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "请输入您要搜索的电影！")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
