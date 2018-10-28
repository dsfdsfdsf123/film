package com.money.film.Service.impl;

import com.money.film.Service.WebSiteInfoService;
import com.money.film.entity.WebSiteInfo;
import com.money.film.repository.WebSiteInfoRepository;
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
 * @create 2018/10/27 21:30
 **/
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public List<WebSiteInfo> list(WebSiteInfo webSiteInfo, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize, Sort.Direction.DESC,"publishDate");
        Page<WebSiteInfo> webSiteInfoPage = webSiteInfoRepository.findAll(new Specification<WebSiteInfo>() {
            @Override
            public Predicate toPredicate(Root<WebSiteInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSiteInfo!=null){
                    if (StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                        predicate.getExpressions().add(cb.like(root.get("info"),"%"+webSiteInfo.getInfo().trim()+"%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return webSiteInfoPage.getContent();
    }

    @Override
    public Long getCount(WebSiteInfo webSiteInfo) {
        Long count = webSiteInfoRepository.count(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (webSiteInfo!=null){
                    if (StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                        predicate.getExpressions().add(cb.like(root.get("info"),"%"+webSiteInfo.getInfo().trim()+"%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public List<WebSiteInfo> getByFilmId(Integer filmId) {
        return webSiteInfoRepository.getByFilmId(filmId);
    }

    @Override
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
        return webSiteInfoRepository.getByWebSiteId(webSiteId);
    }

    @Override
    public void save(WebSiteInfo webSiteInfo) {
        webSiteInfoRepository.save(webSiteInfo);
    }

    @Override
    public void delete(Integer id) {
        webSiteInfoRepository.deleteById(id);
    }
}
