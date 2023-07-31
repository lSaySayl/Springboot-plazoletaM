package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.MenuErrorDTO;
import com.example.plazoleta.dto.error.OrderErrorDTO;
import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.dto.general.OrderDTO;
import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.dto.response.OrderResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("restaurantAPI/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order dataOrder) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dataOrder));
        }catch (Exception e){
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<OrderDTO> updatedOrder(@PathVariable Long id, @RequestBody Order order){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderPreparation(id, order));
        }catch (Exception e){
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }
    @PutMapping("/updateOrderReady/{id}")
    public ResponseEntity<OrderDTO> updateOrderReady(@PathVariable long id, @RequestBody Order order){
      try {
          return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderReady(id, order));
      }catch (Exception e){
          OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
          orderErrorDTO.setMessage(e.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
      }
    }
    @GetMapping
    public ResponseEntity <List<OrderResponseDTO>> getPaginatedAndFilterOrder (
            @RequestParam () String site,
            @RequestParam () String status,
            @RequestParam () int numberOfRecords
    ) {
        try {
            // Llamamos al servicio para obtener la respuesta paginada
            Page<OrderResponseDTO> orderPages = orderService.getOrderForStatusAndSite(site, status, numberOfRecords);

            // Creamos una instancia de PlatoRespuestaPaginadaDTO y le pasamos la lista de platos obtenida del Page
            List<OrderResponseDTO> listOrders = orderPages.getContent();

            return ResponseEntity.status(HttpStatus.OK).body(listOrders);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
