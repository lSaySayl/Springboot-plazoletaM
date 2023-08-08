package com.example.plazoleta.services;

import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.maps.MenuMaps;
import com.example.plazoleta.repository.RepositoryMenu;
import com.example.plazoleta.validations.MenuValidation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest {
    private Menu menu;
    private MenuResponseDTO menuResponseDTO;
    @Mock
    RepositoryMenu repositoryMenu;

    @InjectMocks
    MenuService menuService;

    @Mock
    MenuValidation menuValidation;

    @Mock
    MenuMaps menuMaps;


    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        Long id = 1L;
        char rol = 'A';
        String name = "Helado";
        Integer price = 200;
        String description = "Esta es un descripcion de prueba";
        String url = "Esra es una url de prueba";
        String category = "postre";
        boolean status = true;
        String site = "medellin";
        double prepatationTime = 2.3;
        menu = new Menu(id, rol, name, price, description, url, site, category, prepatationTime, status);


    }

    @Test
    void createMenuCorrect() throws Exception{
        when(repositoryMenu.save(menu)).thenReturn(menu);
        MenuResponseDTO result = menuService.createMenu(menu);
        assertEquals(result, menuResponseDTO);
    }
    @Test
    void createMenuPriceNegative() throws Exception{
        when(menuValidation.validatePrice(anyInt())).thenReturn(true);
        menu.setPrice(-1);
        assertThrows(Exception.class, ()-> menuService.createMenu(menu));
    }
    @Test
    void createMenuWrongRol() throws Exception{
        menu.setRol('B');
        assertThrows(Exception.class, ()->menuService.createMenu(menu));
    }
    @Test
    void createMenuWithoutSite() throws Exception{
        when(menuValidation.validatePrice(anyInt())).thenReturn(false);
        menu.setSite(null);
        assertThrows(Exception.class, ()-> menuService.createMenu(menu));
    }
    @Test
    void createMenuWithoutPrice() throws Exception{
        when(menuValidation.validatePrice(anyInt())).thenReturn(false);
        menu.setPrice(null);
        assertThrows(Exception.class, ()-> menuService.createMenu(menu));
    }
    @Test
    void createMenuWithoutName() throws Exception{
        when(menuValidation.validatePrice(anyInt())).thenReturn(false);
        menu.setName(null);
        assertThrows(Exception.class, ()-> menuService.createMenu(menu));
    }
    @Test
    void createMenuWithoutCategory() throws Exception{
        when(menuValidation.validatePrice(anyInt())).thenReturn(false);
        menu.setCategory(null);
        assertThrows(Exception.class, ()-> menuService.createMenu(menu));
    }


    @Test
    void updateStatus() throws Exception{
        when(repositoryMenu.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(repositoryMenu.save(menu)).thenReturn(menu);
        MenuResponseDTO responseDTO = menuService.updateStatus(menu.getId(),menu);
        assertEquals(responseDTO,menuResponseDTO);
    }
    @Test
    void updateStatusWrongRol() throws Exception{
        menu.setRol('B');
        assertThrows(Exception.class, ()->menuService.updateStatus(menu.getId(), menu));
    }
    @Test
    void updateStatusIdNotExist() throws Exception{
        Optional<Menu> menuOptional = Optional.empty();
        when(repositoryMenu.findById(menu.getId())).thenReturn(Optional.empty());
        when(menuValidation.validateIsIdIsPresent(menuOptional)).thenReturn(true);
        assertThrows(Exception.class,()->menuService.updateStatus(menu.getId(), menu));
    }

    @Test
    void getMenusForCategoryAndSite() throws Exception{
        int numberOfRecords = 1;
        Pageable pagerList = PageRequest.of(0,numberOfRecords);
        Page<Menu> menuPage = new PageImpl<>(List.of(menu));
        when(repositoryMenu.findByCategoryAndSite(menu.getCategory(),menu.getSite(),pagerList)).thenReturn(menuPage);
        when(menuMaps.toMenuResponseDto(menu)).thenReturn(menuResponseDTO);
        Page<MenuResponseDTO> result = menuService.getMenusForCategoryAndSite(menu.getCategory(), menu.getSite(),numberOfRecords);
        assertNotNull(result);
        assertEquals(1,result.getTotalElements());
        assertEquals(menuResponseDTO,result.getContent().get(0));
    }

    @Test
    void getMenusForCategoryAndSiteWithError(){
        int numberOfRecords = 0;
        assertThrows(Exception.class,()->menuService.getMenusForCategoryAndSite(menu.getCategory(), menu.getSite(), numberOfRecords));
    }
    @Test
    void getMenusForCategory() throws Exception{
        int numberOfRecords = 1;
        Pageable pagerList = PageRequest.of(0,numberOfRecords);
        Page<Menu> menuPage = new PageImpl<>(List.of(menu));
        when(repositoryMenu.findByCategory(menu.getCategory(),pagerList)).thenReturn(menuPage);
        when(menuMaps.toMenuResponseDto(menu)).thenReturn(menuResponseDTO);
        Page<MenuResponseDTO> result = menuService.getMenusForCategory(menu.getCategory(),numberOfRecords);
        assertNotNull(result);
        assertEquals(1,result.getTotalElements());
        assertEquals(menuResponseDTO,result.getContent().get(0));
    }
    @Test
    void getMenusForCategoryWithError() throws Exception{
        int numberOfRecords = 0;
        assertThrows(Exception.class,()->menuService.getMenusForCategory(menu.getCategory(),numberOfRecords));
    }

    @Test
    void getMenusForSite() throws Exception{
        int numberOfRecords = 1;
        Pageable pagerList = PageRequest.of(0,numberOfRecords);
        Page<Menu> menuPage = new PageImpl<>(List.of(menu));
        when(repositoryMenu.findBySite(menu.getSite(),pagerList)).thenReturn(menuPage);
        when(menuMaps.toMenuResponseDto(menu)).thenReturn(menuResponseDTO);
        Page<MenuResponseDTO> result = menuService.getMenusForSite(menu.getSite(),numberOfRecords);
        assertNotNull(result);
        assertEquals(1,result.getTotalElements());
        assertEquals(menuResponseDTO,result.getContent().get(0));
    }
    @Test
    void getMenusForSiteWithError() throws Exception{
        int numberOfRecords = 0;
        assertThrows(Exception.class,()->menuService.getMenusForSite(menu.getSite(),numberOfRecords));
    }

    @Test
    void updateMenu() throws Exception{
        when(repositoryMenu.findById(menu.getId())).thenReturn(Optional.of(menu));
        when(repositoryMenu.save(menu)).thenReturn(menu);
        MenuResponseDTO responseDTO =menuService.updateMenu(menu.getId(),menu);
        assertEquals(responseDTO,menuResponseDTO);
    }
    @Test
    void updateMenuWrongRol() throws Exception{
        menu.setRol('B');
        assertThrows(Exception.class, ()->menuService.updateMenu(menu.getId(), menu));
    }
    @Test
    void updateMenuIdNotExist() throws Exception{
        menu.setId(0L);
        assertThrows(Exception.class, ()->menuService.updateMenu(menu.getId(),menu));
    }
}