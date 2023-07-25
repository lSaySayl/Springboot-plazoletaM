package com.example.plazoleta.dto.response;

import java.util.List;

public class OrderResponseDTO {

    private List<OrderResponseDTO> menus;
    private String status;

    public List<OrderResponseDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<OrderResponseDTO> menus) {
        this.menus = menus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
