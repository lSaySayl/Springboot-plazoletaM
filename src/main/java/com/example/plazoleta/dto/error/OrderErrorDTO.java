package com.example.plazoleta.dto.error;

import com.example.plazoleta.dto.general.OrderDTO;

public class OrderErrorDTO extends OrderDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
