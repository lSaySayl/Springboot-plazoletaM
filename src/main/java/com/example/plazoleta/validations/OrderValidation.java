package com.example.plazoleta.validations;
import com.example.plazoleta.entity.Order;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderValidation {
    public  boolean validateIsIdIsPresent(Optional<?> id){
        if (!id.isPresent() || id.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static Boolean validateRequired(Order order){
        return  order.getSite() == null || order.getSite().isEmpty() ||
                order.getDetails() == null || order.getDetails().isEmpty();
    }
    public  void validarLetra(Character letra) throws Exception {
        if (!letra.equals('A') && !letra.equals('U')) {
            throw new Exception("No tienes permiso para modificar el pedido");
        }
    }
}

