package com.example.plazoleta.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_entity")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(name = "rol", nullable = false)
    private char rol;

    @Column(name="site",nullable = false)
    private String site;

    @Column(name="status",nullable = false)
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<OrderDetail> details;

    //consttructor vacío

    public Order() {
    }


    //constructor lleno

    public Order(Long idOrder, char rol, String site, String status, List<OrderDetail> details) {
        this.idOrder = idOrder;
        this.rol = rol;
        this.site = site;
        this.status = status;
        this.details = details;
    }


    //getters y setters


    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public char getRol() {
        return rol;
    }

    public void setRol(char rol) {
        this.rol = rol;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
