package com.example.plazoleta.dto.error;

import com.example.plazoleta.dto.general.MenuDTO;

public class MenuErrorDTO extends MenuDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
