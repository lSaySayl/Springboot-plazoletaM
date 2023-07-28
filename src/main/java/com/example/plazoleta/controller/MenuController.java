package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.MenuErrorDTO;
import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurantAPI/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuDTO> createMenu(@RequestBody Menu dataMenu) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(dataMenu));
        }catch (Exception e){
            MenuErrorDTO menuErrorDTO = new MenuErrorDTO();
            menuErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(menuErrorDTO);
        }
    }
    @PutMapping("/updateMenu/{id}")
    public ResponseEntity<MenuDTO> updatedMenu(@PathVariable Long id, @RequestBody Menu menu){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(menuService.updateMenu(id, menu));
        }catch (Exception e){
            MenuErrorDTO menuErrorDTO = new MenuErrorDTO();
            menuErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(menuErrorDTO);
        }
    }

    @PutMapping("updateStatus/{id}")
    public  ResponseEntity<MenuDTO> updateMenuStatus(@PathVariable Long id, @RequestBody Menu menu){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.updateStatus(id, menu));
        }catch (Exception e){
            MenuErrorDTO menuErrorDTO = new MenuErrorDTO();
            menuErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(menuErrorDTO);
        }
    }
}
