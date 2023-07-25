package com.example.plazoleta.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private char rol;

    private List<Menu> menus;

    private String site;

    private String status;

    //consttructor vacío

    public Order() {
    }


    //constructor lleno

    public Order(Long id, char rol, List<Menu> menus, String site, String status) {
        this.id = id;
        this.rol = rol;
        this.menus = menus;
        this.site = site;
        this.status = status;
    }


    //getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
