package com.example.plazoleta.validations;

import com.example.plazoleta.entity.Order;

public class OrderValidation {
    public static Boolean validateRequired(Order order){
        return  order.getSite() == null || order.getSite().isEmpty() ||
                order.getDetails() == null || order.getDetails().isEmpty();
    }

}
