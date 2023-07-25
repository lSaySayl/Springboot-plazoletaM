package com.example.plazoleta.repository;

import com.example.plazoleta.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMenu extends JpaRepository <Menu, Long> {
}
