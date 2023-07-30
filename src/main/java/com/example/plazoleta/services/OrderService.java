package com.example.plazoleta.services;
import com.example.plazoleta.dto.response.OrderResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.entity.OrderDetail;

import com.example.plazoleta.maps.OrderMaps;
import com.example.plazoleta.validations.OrderValidation;
import com.example.plazoleta.repository.RepositoryMenu;
import com.example.plazoleta.repository.RepositoryOrder;
import com.example.plazoleta.validations.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    RepositoryOrder repositoryOrder;
    @Autowired
    OrderMaps orderMaps;
    @Autowired
    OrderValidation orderValidation;

    @Autowired
    RepositoryMenu repositoryMenu;


//    public OrderResponseDTO updateStatusOrder(Long idOrder, Order dataOrder) throws Exception {
//        try {
//            if (!dataOrder.getApprovalRol().equals('A')) {
//                throw new Exception("No tiene permisos para cambiar el estado a este pedido");
//            }
//            Optional<Order> orderOptional = repositoryOrder.findById(idOrder);
//            if (orderValidation.validateIsIdIsPresent(orderOptional)) {
//                throw new Exception("El pedido no existe");
//            }
//            Order orderExist = orderOptional.get();
//            if (orderExist.getStatus() != ("Listo") && dataOrder.getStatus() != ("Entregado")) {
//                throw new Exception("Solamente puede actualizar el estado del pedido a entregado cuando se encuentre listo");
//            }
//            orderExist.setStatus(dataOrder.getStatus());
//            return orderMaps.toOrderResponseDto(repositoryOrder.save(orderExist));
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }

public OrderResponseDTO createOrder (Order dataOrder) throws Exception {
    try {
        if (dataOrder.getRol() != ('U')) {
            throw new Exception("No tiene permisos para crear una Orden");
        }
        if (OrderValidation.validateRequired(dataOrder)) {
            throw new Exception("Campos obligatorios vacios, verifique nuevamente");
        }
        for (OrderDetail detail : dataOrder.getDetails()) {
            Long idOrder = detail.getMenu().getId();
            Optional<Menu> menuOptional = repositoryMenu.findById(idOrder);
            detail.getMenu().setName(menuOptional.get().getName());
        }
        return orderMaps.toOrderResponseDto(repositoryOrder.save(dataOrder));

    } catch (Exception error) {
        throw new Exception(error.getMessage());
    }
    public OrderResponseDTO updateOrderPreparation(Long idOrder, Order dataOrder) throws Exception{
        try{
            if(dataOrder.getRol()!=('A')){
                throw new Exception("El rol no esta autorizado para actualizar el pedido");
            }
            Optional<Order> orderOptional = repositoryOrder.findById(idOrder);
            if (orderOptional.isEmpty()) {
                throw new Exception("No existe el pedido");
            }
            Order orderExist = orderOptional.get();
            if (dataOrder.getStatus()!=("Preparacion"))
                throw new Exception("El estado no puede ser diferente de preparación");
            orderExist.setStatus(dataOrder.getStatus());
            return orderMaps.toOrderResponseDto(repositoryOrder.save(orderExist));


        }catch (Exception error){
            throw new Exception(error.getMessage());

}
        public Page<OrderResponseDTO> getOrderForStatusAndSite (Character rol, String side, String status,
  int numberOfRecords) throws Exception {
            try {
                Pageable pagerList = PageRequest.of(0, numberOfRecords);
                Page<Order> orderPagerList = repositoryOrder.findByStatusAndSite(side, status, pagerList);
                return orderPagerList.map(order -> orderMaps.toOrderResponseDto(order));

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

  
        }

}