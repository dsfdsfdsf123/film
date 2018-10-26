package com.money.film.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author liugang
 * @create 2018/10/23 22:46
 **/
@Entity
@Table(name = "t_webSite")
@Data
public class WebSite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 300)
    private String url;

    @Column(length = 100)
    private String name;
}
