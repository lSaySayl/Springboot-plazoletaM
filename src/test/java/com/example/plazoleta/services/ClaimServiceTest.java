package com.example.plazoleta.services;

import com.example.plazoleta.dto.response.ClaimResponseDTO;
import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.entity.Claim;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.maps.ClaimMaps;
import com.example.plazoleta.repository.RepositoryClaim;
import com.example.plazoleta.repository.RepositoryOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class ClaimServiceTest {
    private Claim claim;
    private ClaimResponseDTO claimResponseDTO;
    private Order order = new Order();
    @Mock
    RepositoryClaim repositoryClaim;
    @Mock
    ClaimMaps claimMaps;
    @InjectMocks
    ClaimService claimService;
    @Mock
    RepositoryOrder repositoryOrder;

    @BeforeEach
    void setup() throws Exception{
        MockitoAnnotations.openMocks(this);

        order.setIdOrder(1L);

        Long id = 1L;
        String site = "Ejemplo site";
        String status = "Generada";
        String reason = "Ejemplo de razon";
        String response = "Ejemplo de respuesta";
        Character rol = 'U';
        claim = new Claim(id,order,site,status,reason,response,rol);
    }

    @Test
    void createClaim() throws Exception{
        when(repositoryOrder.findById(claim.getOrder().getIdOrder())).thenReturn(Optional.of(order));
        when(repositoryClaim.save(claim)).thenReturn(claim);
        ClaimResponseDTO result = claimService.createClaim(claim);
        assertEquals(result,claimResponseDTO);
    }
    @Test
    void createClaimIdNotExist() throws Exception{
        assertThrows(Exception.class, ()->claimService.createClaim(claim));
    }
    @Test
    void createClaimWrongRol() throws Exception{
        when(repositoryOrder.findById(claim.getOrder().getIdOrder())).thenReturn(Optional.of(order));
        claim.setRol('A');
        assertThrows(Exception.class, ()->claimService.createClaim(claim));
    }

    @Test
    void getClaimsInStateGenerated() throws Exception{
        List<Claim> claimList = new ArrayList<>();
        claimList.add(claim);
        when(repositoryClaim.findByStatus("Generada")).thenReturn(claimList);
        List<ClaimResponseDTO> result = claimService.getClaimsInStateGenerated();
        assertNotNull(result);

    }

    @Test
    void updateStatusClaim() throws Exception{
        claim.setRol('A');
        when(repositoryClaim.findById(claim.getId())).thenReturn(Optional.of(claim));
        when(repositoryClaim.save(claim)).thenReturn(claim);
        ClaimResponseDTO result = claimService.updateStatusClaim(claim.getId(), claim);

    }
    @Test
    void updateStautusClaimWithWrongRol(){
        assertThrows(Exception.class,()->claimService.updateStatusClaim(claim.getId(),claim));
    }
    @Test
    void updateStatusClaimIdNotExist(){
        when(repositoryClaim.findById(claim.getId())).thenReturn(Optional.of(claim));
        assertThrows(Exception.class,()->claimService.updateStatusClaim(claim.getId(), claim));
    }
    @Test
    void updateStatusClaimReponseNull(){
        claim.setRol('A');
        when(repositoryClaim.findById(claim.getId())).thenReturn(Optional.of(claim));
        claim.setResponse(null);
        assertThrows(Exception.class,()->claimService.updateStatusClaim(claim.getId(), claim));
    }
}