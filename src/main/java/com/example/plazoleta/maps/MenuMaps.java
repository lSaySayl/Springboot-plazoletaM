package com.example.plazoleta.maps;

import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMaps {

    @Mappings({
            @Mapping(source="name",target = "name"),
            @Mapping(source="price",target = "price"),
            @Mapping(source="description",target = "description"),
            @Mapping(source="url",target = "url"),
            @Mapping(source="category",target = "category")

    })

    public MenuResponseDTO toMenuResponseDto(Menu menu);

    public List <MenuResponseDTO> toMenuResponseDtos(List<Menu> menus);

}
