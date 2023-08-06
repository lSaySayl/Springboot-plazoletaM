package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.MenuErrorDTO;
import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.services.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a menu")
    @ApiResponse(responseCode = "201", description = "Menu created successfully", content = @Content(schema = @Schema(implementation = MenuDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = MenuErrorDTO.class)))
    @PostMapping("/createMenu")
    public ResponseEntity<MenuDTO> createMenu(@RequestBody Menu dataMenu) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(dataMenu));
        }catch (Exception e){
            MenuErrorDTO menuErrorDTO = new MenuErrorDTO();
            menuErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(menuErrorDTO);
        }
    }
    @Operation(summary = "Update a menu by ID")
    @ApiResponse(responseCode = "200", description = "Menu updated successfully", content = @Content(schema = @Schema(implementation = MenuDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = MenuErrorDTO.class)))
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

    @Operation(summary = "Update menu status by ID")
    @ApiResponse(responseCode = "200", description = "Menu status updated successfully", content = @Content(schema = @Schema(implementation = MenuDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = MenuErrorDTO.class)))
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




    @Operation(summary = "Get paginated and filtered menus")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MenuResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = MenuErrorDTO.class)))

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
