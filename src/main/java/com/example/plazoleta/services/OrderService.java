package com.example.plazoleta.services;

import com.example.plazoleta.dto.response.OrderResponseDTO;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.maps.OrderMaps;
import com.example.plazoleta.repository.RepositoryOrder;
import com.example.plazoleta.validations.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
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

    public OrderResponseDTO updateStatusOrder(Long idOrder, Order dataOrder) throws Exception{
        try{
            if (!dataOrder.getApprovalRol().equals('A')){
                throw new Exception("No tiene permisos para cambiar el estado a este pedido");
            }
            Optional<Order> orderOptional = repositoryOrder.findById(idOrder);
            if (orderValidation.validateIsIdIsPresent(orderOptional)){
                throw new Exception("El pedido no existe");
            }
            Order orderExist = orderOptional.get();
            if (orderExist.getStatus()!=("Listo")&& dataOrder.getStatus()!=("Entregado")){
                throw new Exception("Solamente puede actualizar el estado del pedido a entregado cuando se encuentre listo");
            }
            orderExist.setStatus(dataOrder.getStatus());
            return orderMaps.toOrderResponseDto(repositoryOrder.save(orderExist));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
