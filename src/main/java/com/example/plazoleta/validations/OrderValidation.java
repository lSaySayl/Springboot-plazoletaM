package com.example.plazoleta.validations;

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
}
