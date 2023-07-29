package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.MenuErrorDTO;
import com.example.plazoleta.dto.error.OrderErrorDTO;
import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.dto.general.OrderDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("restaurantAPI/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order dataOrder) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dataOrder));
        }catch (Exception e){
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }
}
