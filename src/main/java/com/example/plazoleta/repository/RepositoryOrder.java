package com.example.plazoleta.repository;

import com.example.plazoleta.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryOrder extends JpaRepository <Order, Long> {
}
