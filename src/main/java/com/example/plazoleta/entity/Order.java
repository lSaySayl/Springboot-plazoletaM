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
    private Character rol;

    @Column(name = "approvalRol", nullable = false)
    private Character approvalRol;

    @Column(name="site",nullable = false)
    private String site;

    @Column(name="status",nullable = false)
    private String status = "Pendiente";

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private List<OrderDetail> details;

    @OneToOne(mappedBy = "order")
    private Claim claim;

    @Column(name="reasonForCancellation")
    private String reasonForCancellation;

    //constructor vacío

    public Order() {
    }


    //constructor lleno

    public Order(Long idOrder, Character rol, Character approvalRol, String site, String status, List<OrderDetail> details, Claim claim, String reasonForCancellation) {
        this.idOrder = idOrder;
        this.rol = rol;
        this.approvalRol = approvalRol;
        this.site = site;
        this.status = status;
        this.details = details;
        this.claim = claim;
        this.reasonForCancellation = reasonForCancellation;
    }


    //getters y setters

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Character getRol() {
        return rol;
    }

    public void setRol(Character rol) {
        this.rol = rol;
    }

    public Character getApprovalRol() {
        return approvalRol;
    }

    public void setApprovalRol(Character approvalRol) {
        this.approvalRol = approvalRol;
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

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public String getReasonForCancellation() {
        return reasonForCancellation;
    }

    public void setReasonForCancellation(String reasonForCancellation) {
        this.reasonForCancellation = reasonForCancellation;
    }
}
