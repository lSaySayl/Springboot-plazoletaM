package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.ClaimErrorDTO;
import com.example.plazoleta.dto.general.ClaimDTO;
import com.example.plazoleta.dto.response.ClaimResponseDTO;
import com.example.plazoleta.entity.Claim;
import com.example.plazoleta.repository.RepositoryClaim;
import com.example.plazoleta.services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurantAPI/claim")
public class ClaimController {

    @Autowired
    ClaimService claimService;
    @PostMapping
    public ResponseEntity<ClaimDTO> createClaim (@RequestBody Claim claim){
       try {
           return ResponseEntity.status(HttpStatus.CREATED).body(claimService.createClaim(claim));
       }catch (Exception e){
           ClaimErrorDTO claimErrorDTO = new ClaimErrorDTO();
           claimErrorDTO.setMessage(e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(claimErrorDTO);
       }
    }
    @GetMapping
    public ResponseEntity<List<ClaimResponseDTO>> getClaims(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(claimService.getClaimsInStateGenerated());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaimDTO> updateStatusClaim(@PathVariable Long id, @RequestBody Claim claim){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(claimService.updateStatusClaim(id, claim));
        }catch (Exception e){
            ClaimErrorDTO claimErrorDTO = new ClaimErrorDTO();
            claimErrorDTO.setMessage(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(claimErrorDTO);
        }
    }

}
