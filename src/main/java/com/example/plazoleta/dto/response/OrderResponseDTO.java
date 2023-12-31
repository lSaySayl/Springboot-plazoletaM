package com.example.plazoleta.dto.response;

import com.example.plazoleta.dto.general.OrderDTO;
import com.example.plazoleta.dto.general.OrderDetailDTO;

import java.util.List;

public class OrderResponseDTO extends OrderDTO {
    private Long idOrder;
    private String site;

    private List<OrderDetailDTO> details;
    private String status;

    private double timeOrder;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<OrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailDTO> details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(double timeOrder) {
        this.timeOrder = timeOrder;
    }
}
