package com.example.plazoleta.repository;

import com.example.plazoleta.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryClaim extends JpaRepository<Claim, Long> {
}
