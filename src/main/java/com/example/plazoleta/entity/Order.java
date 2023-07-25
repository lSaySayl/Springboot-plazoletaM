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

   @OneToMany(mappedBy = "order")
    private List<Menu> menus;

    @Column(name="site",nullable = false)
    private String site;

    @Column(name="status",nullable = false)
    private String status;

    //consttructor vacío

    public Order() {
    }


    //constructor lleno

    public Order(Long idOrder, char rol, List<Menu> menus, String site, String status) {
        this.idOrder = idOrder;
        this.rol = rol;
        this.menus = menus;
        this.site = site;
        this.status = status;
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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
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
}
