package com.money.film.Service.impl;

import com.money.film.Service.WebSiteService;
import com.money.film.entity.WebSite;
import com.money.film.repository.WebSiteRepository;
import com.money.film.util.StringUtil;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
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
 * @create 2018/10/27 0:28
 **/
@Service("webSiteService")
public class WebSiteServiceImpl implements WebSiteService
{

    @Resource
    private WebSiteRepository webSiteRepository;


    @Override
    public List<WebSite> list(WebSite webSite, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize, Sort.Direction.ASC,"id");
        Page<WebSite> webSitePage = webSiteRepository.findAll(new Specification<WebSite>() {
            @Override
            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSite!=null){
                    if (StringUtil.isNotEmpty(webSite.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+webSite.getName().trim()+"%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())){
                        predicate.getExpressions().add(cb.like(root.get("url"),"%"+webSite.getUrl().trim()+"%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return webSitePage.getContent();
    }

    @Override
    public Long getCount(WebSite webSite) {
        Long count = webSiteRepository.count(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSite!=null){
                    if (StringUtil.isNotEmpty(webSite.getName())){
                        predicate.getExpressions().add(cb.like(root.get("name"),"%"+webSite.getName().trim()+"%"));
                    }
                    if (StringUtil.isNotEmpty(webSite.getUrl())){
                        predicate.getExpressions().add(cb.like(root.get("url"),"%"+webSite.getUrl().trim()+"%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void save(WebSite webSite) {
        webSiteRepository.save(webSite);
    }

    @Override
    public void delete(Integer id) {
        webSiteRepository.deleteById(id);
    }
}
