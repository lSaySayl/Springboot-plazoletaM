package com.example.plazoleta.dto.response;

import com.example.plazoleta.dto.general.OrderDTO;

import java.util.List;

public class OrderResponseDTO extends OrderDTO {

    private List<MenuResponseDTO> menus;
    private String status;

    public List<MenuResponseDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuResponseDTO> menus) {
        this.menus = menus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
