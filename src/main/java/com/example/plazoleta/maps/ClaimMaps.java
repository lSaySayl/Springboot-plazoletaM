package com.example.plazoleta.maps;

import com.example.plazoleta.dto.response.ClaimResponseDTO;
import com.example.plazoleta.entity.Claim;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaimMaps {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "reason", target = "reason"),
            @Mapping(source = "response", target = "response")
    })
    public ClaimResponseDTO toClaimResponseDTO(Claim claim);
    public List<ClaimResponseDTO> toClaimResponseDTOs(List<Claim> claims);
}
