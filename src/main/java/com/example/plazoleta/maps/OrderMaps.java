package com.example.plazoleta.maps;

import com.example.plazoleta.dto.general.OrderDetailDTO;
import com.example.plazoleta.dto.response.OrderResponseDTO;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMaps {
    @Mappings({
            @Mapping(source = "idOrder", target = "idOrder"),
            @Mapping(source = "site", target = "site"),
            @Mapping(source="status",target = "status"),
            @Mapping(source = "timeOrder", target = "timeOrder"),
            @Mapping(target = "details", qualifiedByName = "toListOrderDetail")
    })
    public OrderResponseDTO toOrderResponseDto(Order order);
    public List<OrderResponseDTO> toOrderResponseDtos(List<Order> orders);

    @Named("toListOrderDetail")
    default List<OrderDetailDTO> toListOrderDetail(List<OrderDetail> details){
        return details.stream()
                .map(this::toDetailOrder)
                .collect(Collectors.toList());
    }

    @Mapping(target = "name", source = "menu.name")
    @Mapping(target = "quantity", source = "quantity")
    OrderDetailDTO toDetailOrder(OrderDetail orderDetail);
}
