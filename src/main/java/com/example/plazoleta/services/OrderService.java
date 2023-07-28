package com.example.plazoleta.services;

import com.example.plazoleta.maps.OrderMaps;
import com.example.plazoleta.repository.RepositoryOrder;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {

    @Autowired
    RepositoryOrder repositoryOrder;

    @Autowired
    OrderMaps orderMaps;


}
