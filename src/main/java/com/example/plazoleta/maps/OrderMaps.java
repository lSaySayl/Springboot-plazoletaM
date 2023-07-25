package com.example.plazoleta.maps;

import com.example.plazoleta.dto.response.OrderResponseDTO;
import com.example.plazoleta.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMaps {
    @Mappings({
            @Mapping(source="menus",target = "menus"),
            @Mapping(source="status",target = "status")

    })

    public OrderResponseDTO toOrderResponseDto(Order order);

    public List<OrderResponseDTO> toOrderResponseDto(List<Order> orders);
}
