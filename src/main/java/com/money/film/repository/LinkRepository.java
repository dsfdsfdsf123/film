package com.money.film.repository;

import com.money.film.entity.Link;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Link
 * @author liugang
 * @create 2018/10/25 22:39
 **/
public interface LinkRepository extends JpaRepository<Link,Integer> {

    @Query(value = "select * from t_link where link_name like %?1% ",nativeQuery = true)
    List<Link> findByLinkName (String linkName, Pageable pageable);

    @Query(value = "select count(1) from t_link where link_name like %?1% ",nativeQuery = true)
    Long total(String linkName);
}
