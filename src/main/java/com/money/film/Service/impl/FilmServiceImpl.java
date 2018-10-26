package com.money.film.Service.impl;

import com.money.film.Service.FilmService;
import com.money.film.entity.Film;
import com.money.film.repository.FileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liugang
 * @create 2018/10/25 22:03
 **/
@Service("filmService")
public class FilmServiceImpl implements FilmService {

    @Resource
    private FileRepository fileRepository;

    @Override
    public void save(Film film) {
        fileRepository.save(film);
    }
}
