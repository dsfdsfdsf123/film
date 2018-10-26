package com.money.film.Service.impl;

import com.money.film.Service.LinkService;
import com.money.film.entity.Link;
import com.money.film.repository.LinkRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
