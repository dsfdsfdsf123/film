package com.money.film.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 友情链接实体
 * @author liugang
 * @create 2018/10/23 22:24
 **/
@Entity
@Table(name = "t_link")
@Data
public class Link {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(length = 500)
    private String linkName;

    @Column(length = 500)
    private String url;

    private Integer sort;
}
