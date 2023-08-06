package com.example.plazoleta.repository;

import com.example.plazoleta.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMenu extends JpaRepository <Menu, Long> {
    Page<Menu> findByCategoryAndSite(String category, String site, Pageable pagerList);
    Page<Menu> findByCategory(String category,Pageable pagerlist);
    Page<Menu> findBySite(String site,Pageable pagerlist);
}
