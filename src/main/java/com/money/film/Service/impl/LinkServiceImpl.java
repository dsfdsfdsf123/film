package com.money.film.Service.impl;

import com.money.film.Service.LinkService;
import com.money.film.entity.Film;
import com.money.film.entity.Link;
import com.money.film.repository.LinkRepository;
import com.money.film.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 友情链接service实现类
 * @author liugang
 * @create 2018/10/25 22:42
 **/
@Service("linkService")
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkRepository linkRepository;

    @Override
    public List<Link> list(Integer page, Integer pageSize) {
        return linkRepository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    @Override
    public List<Link> listAll() {
        return linkRepository.findAll();
    }

    @Override
    public Long getCount() {
        return linkRepository.count();
    }

    @Override
    public void save(Link link) {
        linkRepository.save(link);
    }

    @Override
    public void delete(Integer id) {
        linkRepository.deleteById(id);
    }

    @Override
    public List<Link> linkList(Integer page, Integer pageSize,String linkName) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return linkRepository.findByLinkName(linkName,pageable);
    }

    @Override
    public Long total(String linkName) {
        return linkRepository.total(linkName);
    }

}
