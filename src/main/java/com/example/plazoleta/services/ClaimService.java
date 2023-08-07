package com.example.plazoleta.services;

import com.example.plazoleta.dto.response.ClaimResponseDTO;
import com.example.plazoleta.entity.Claim;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.maps.ClaimMaps;
import com.example.plazoleta.repository.RepositoryClaim;
import com.example.plazoleta.repository.RepositoryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    @Autowired
    RepositoryClaim repositoryClaim;
    @Autowired
    ClaimMaps claimMaps;
    @Autowired
    RepositoryOrder repositoryOrder;

    public ClaimResponseDTO createClaim(Claim claim) throws Exception{
        try {
            Optional< Order> orderOptional = repositoryOrder.findById(claim.getOrder().getIdOrder());
            if(!orderOptional.isPresent()){
                throw new Exception("La Orden no existe");
            }
            if (!claim.getRol().equals('U')){
                throw new Exception("No tiene permisos para crear una reclamacion");
            }
            return claimMaps.toClaimResponseDTO(repositoryClaim.save(claim));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ClaimResponseDTO> getClaimsInStateGenerated(){
        return claimMaps.toClaimResponseDTOs(repositoryClaim.findByStatus("Generada"));
    }

    public ClaimResponseDTO updateStatusClaim(Long id, Claim claim) throws Exception{
        try {
            Optional<Claim> claimOptional = repositoryClaim.findById(id);
            if (!claimOptional.isPresent()){
                throw new Exception("La reclamacion no existe");
            }
            if (!claim.getRol().equals('A')){
                throw new Exception("No tiene permisos para modificar el estado de una reclamcion");
            }
            Claim claimExist = claimOptional.get();
            claimExist.setStatus(claim.getStatus());
            claimExist.setResponse(claim.getResponse());
            if (claimExist.getResponse()== null){
                throw new Exception("El campo de respuesta no puede quedar vacio");
            }
            return claimMaps.toClaimResponseDTO(repositoryClaim.save(claimExist));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
