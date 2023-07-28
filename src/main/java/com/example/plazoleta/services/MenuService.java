package com.example.plazoleta.services;

import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.maps.MenuMaps;
import com.example.plazoleta.repository.RepositoryMenu;
import com.example.plazoleta.validations.MenuValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    MenuValidation validate;
    @Autowired
    RepositoryMenu repositoryMenu;

    @Autowired
    MenuMaps menuMap;
    public MenuResponseDTO createMenu(Menu dataMenu) throws Exception{
        try{
            if(!dataMenu.getRol().equals('A')){
                throw new Exception("No tiene permisos para crear un plato");
            }else if (validate.validatePrice(dataMenu.getPrice())){
                throw new Exception("El plato no puede tener un precio negativo");
            }
            return menuMap.toMenuResponseDto(repositoryMenu.save(dataMenu));

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
    public MenuResponseDTO updateStatus(Long id, Menu dataMenu) throws Exception{
        try{
            if (!dataMenu.getRol().equals('A')){
                throw new Exception("No tienes permisos para actualizar el estado");
            }
            Optional<Menu> menuOptional = repositoryMenu.findById(id);
            if (validate.validateIsIdIsPresent(menuOptional)){
                throw new Exception("El id no esta presente o es nulo");
            }
            Menu menuExist =  menuOptional.get();
            menuExist.setStatus(dataMenu.getStatus());
            return menuMap.toMenuResponseDto(repositoryMenu.save(menuExist));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<MenuResponseDTO> getMenusForCategoryAndSite(String category, String site, int numberOfRecords) throws Exception{
        try{
            Pageable pagerList = PageRequest.of(0, numberOfRecords);
            Page<Menu> menuPagerList = repositoryMenu.findByCategoryAndSite(category, site, pagerList);
            return null;

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
