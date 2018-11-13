package com.money.film.Service.impl;

import com.money.film.Service.FilmService;
import com.money.film.entity.Film;
import com.money.film.repository.FilmRepository;
import com.money.film.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author liugang
 * @create 2018/10/25 22:03
 **/
@Service("filmService")
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmRepository fileRepository;

    @Override
    public void save(Film film) {
        fileRepository.save(film);
    }

    @Override
    public List<Film> list(Film film, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize, Sort.Direction.DESC,"publishDate");
        Page<Film> filmPage = fileRepository.findAll(new Specification<Film>() {
            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (film!=null){
                    if (StringUtil.isNotEmpty(film.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+film.getName().trim()+"%"));
                    }
                    if (film.getHot()!=null && film.getHot()==1){
                        predicate.getExpressions().add(cb.equal(root.get("hot"),1));
                    }
                }
                return predicate;
            }
        }, pageable);

        return filmPage.getContent();
    }

    @Override
    public Long getCount(Film film) {
        Long count = fileRepository.count(new Specification<Film>() {
            @Override
            public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (film!=null){
                    if (StringUtil.isNotEmpty(film.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+film.getName().trim()+"%"));
                    }
                    if (film.getHot()!=null && film.getHot()==1){
                        predicate.getExpressions().add(cb.equal(root.get("hot"),1));
                    }
                }
                return predicate;
            }
        });


        return count;
    }

    @Override
    public Film findById(Integer id) {
        return fileRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        fileRepository.deleteById(id);
    }

    @Override
    public Film getLast(Integer id) {
        return fileRepository.getLast(id);
    }

    @Override
    public Film getNext(Integer id) {
        return fileRepository.getNext(id);
    }

    @Override
    public List<Film> randomList(Integer n) {
        return fileRepository.randomList(n);
    }

    @Override
    public Film findByFilmId(Integer id) {
        return fileRepository.findByFilmId(id);
    }
}
