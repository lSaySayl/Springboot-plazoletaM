package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.MenuErrorDTO;
import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity <List<MenuResponseDTO>> getPaginatedAndFilterMenu (
            @RequestParam () String category,
            @RequestParam () String site,
            @RequestParam () int numberOfRecords
    ) {
        try {
            // Llamamos al servicio para obtener la respuesta paginada
            Page<MenuResponseDTO> menuPages = menuService.getMenusForCategoryAndSite(category, site, numberOfRecords);

            // Creamos una instancia de PlatoRespuestaPaginadaDTO y le pasamos la lista de platos obtenida del Page
            List<MenuResponseDTO> listMenus = menuPages.getContent();

            return ResponseEntity.status(HttpStatus.OK).body(listMenus);

        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("getForCategory")
    public ResponseEntity <List<MenuResponseDTO>>getMenusForCategory(
            @RequestParam () String category,
            @RequestParam () int numberOfRecords
    ) {
        try {
            // Llamamos al servicio para obtener la respuesta paginada
            Page<MenuResponseDTO> menuPages = menuService.getMenusForCategory(category, numberOfRecords);

            // Creamos una instancia de PlatoRespuestaPaginadaDTO y le pasamos la lista de platos obtenida del Page
            List<MenuResponseDTO> listMenus = menuPages.getContent();

            return ResponseEntity.status(HttpStatus.OK).body(listMenus);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("getForSite")
    public ResponseEntity <List<MenuResponseDTO>>getMenusForSite(
            @RequestParam () String site,
            @RequestParam () int numberOfRecords
    ) {
        try {
            // Llamamos al servicio para obtener la respuesta paginada
            Page<MenuResponseDTO> menuPages = menuService.getMenusForSite(site, numberOfRecords);

            // Creamos una instancia de PlatoRespuestaPaginadaDTO y le pasamos la lista de platos obtenida del Page
            List<MenuResponseDTO> listMenus = menuPages.getContent();

            return ResponseEntity.status(HttpStatus.OK).body(listMenus);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
