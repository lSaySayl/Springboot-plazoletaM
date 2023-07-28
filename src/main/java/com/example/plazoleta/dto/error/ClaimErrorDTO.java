package com.example.plazoleta.dto.error;

import com.example.plazoleta.dto.general.ClaimDTO;

public class ClaimErrorDTO extends ClaimDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
