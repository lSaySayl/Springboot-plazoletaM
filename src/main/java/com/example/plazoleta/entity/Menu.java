package com.example.plazoleta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "menu_entity")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rol" , nullable = false)
    private  char rol;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "price" , nullable = false)
    private Integer price;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "url" , nullable = false)
    private String url;

    @Column(name = "category" , nullable = false)
    private String category;

    private boolean status;

    private String site;

    private double preparationTime;

    @ManyToOne(optional = false)
    @JoinColumn(name="idOrder", nullable = false)
    private Order order;

    //Constructor vacío

    public Menu() {
    }


    //Constructor con parámetros

    public Menu(Long id, char rol, String name, Integer price, String description, String url, String category, boolean status, String site, double preparationTime, Order order) {
        this.id = id;
        this.rol = rol;
        this.name = name;
        this.price = price;
        this.description = description;
        this.url = url;
        this.category = category;
        this.status = status;
        this.site = site;
        this.preparationTime = preparationTime;
        this.order = order;
    }


    //Getters y Setters


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(double preparationTime) {
        this.preparationTime = preparationTime;
    }
}
