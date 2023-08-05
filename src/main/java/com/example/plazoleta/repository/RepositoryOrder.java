package com.example.plazoleta.repository;

import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryOrder extends JpaRepository <Order, Long> {
    Page<Order> findByStatusAndSite(String Status, String site, Pageable pagerList);

    Page<Order> findByStatus(String Status, Pageable pagerList);

}
