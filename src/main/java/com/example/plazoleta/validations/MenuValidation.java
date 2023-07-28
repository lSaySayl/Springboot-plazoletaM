package com.example.plazoleta.validations;

import com.example.plazoleta.repository.RepositoryMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MenuValidation {

    public boolean validatePrice(Integer price){
        if (price<0){
            return true;
        }else {
            return false;
        }
    }
    public  boolean validateIsIdIsPresent(Optional<?> id){
        if (!id.isPresent() || id.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
