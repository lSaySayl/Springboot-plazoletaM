package com.example.plazoleta.controller;

import com.example.plazoleta.dto.error.MenuErrorDTO;
import com.example.plazoleta.dto.error.OrderErrorDTO;
import com.example.plazoleta.dto.general.MenuDTO;
import com.example.plazoleta.dto.general.OrderDTO;
import com.example.plazoleta.dto.response.ClaimResponseDTO;
import com.example.plazoleta.dto.response.MenuResponseDTO;
import com.example.plazoleta.dto.response.OrderResponseDTO;
import com.example.plazoleta.entity.Menu;
import com.example.plazoleta.entity.Order;
import com.example.plazoleta.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("restaurantAPI/order")
@Tag(name = "Order Management", description = "Endpoints related to order management")
public class OrderController {
    @Autowired
    OrderService orderService;


    @Operation(summary = "Create an order")
    @ApiResponse(responseCode = "201", description = "Order created successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order dataOrder) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dataOrder));
        } catch (Exception e) {
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }

    @Operation(summary = "Update an order by ID")
    @ApiResponse(responseCode = "200", description = "Order updated successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))
    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<OrderDTO> updatedOrder(@PathVariable Long id, @RequestBody Order order) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderPreparation(id, order));
        } catch (Exception e) {
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }

    @Operation(summary = "Update an order to set it as ready")
    @ApiResponse(responseCode = "200", description = "Order updated successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))
    @PutMapping("/updateOrderReady/{id}")
    public ResponseEntity<OrderDTO> updateOrderReady(@PathVariable long id, @RequestBody Order order) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderReady(id, order));
        } catch (Exception e) {
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }

    @Operation(summary = "Get paginated and filtered orders",
            description = "This endpoint retrieves paginated and filtered orders based on site and status.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OrderResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getPaginatedAndFilterOrder(
            @RequestParam() String site,
            @RequestParam() String status,
            @RequestParam() int numberOfRecords
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

    @Operation(summary = "Update an order to set it as canceled")
    @ApiResponse(responseCode = "200", description = "Order updated successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))

    @PutMapping("/updateOrderCanceled/{id}")
    public ResponseEntity<OrderDTO> updateOrderCanceled(@PathVariable Long id, @RequestBody Order order) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderCanceled(id, order));
        } catch (Exception e) {
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }


    @Operation(summary = "Update an order to set it as delivered")
    @ApiResponse(responseCode = "200", description = "Order updated successfully", content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))

    @PutMapping("/updateOrderDelivered/{id}")
    public ResponseEntity<OrderDTO> updateOrderDelivered(@PathVariable Long id, @RequestBody Order order) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderDelivered(id, order));
        } catch (Exception e) {
            OrderErrorDTO orderErrorDTO = new OrderErrorDTO();
            orderErrorDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderErrorDTO);
        }
    }
    @Operation(summary = "Get orders by status",
            description = "This endpoint retrieves orders based on their status.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OrderResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))

    @GetMapping("/getForStatus")
    public ResponseEntity<List<OrderResponseDTO>> getOrderForStatus(@RequestParam() String status, @RequestParam() int numberOfRecords) {
        try {
            Page<OrderResponseDTO> orderPages = orderService.getOrderForStatus(status, numberOfRecords);
            List<OrderResponseDTO> orderList = orderPages.getContent();
            return ResponseEntity.status(HttpStatus.OK).body(orderList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


//
//    @Operation(summary = "Get orders that are ready")
//    @ApiResponse(responseCode = "200", description = "Order updated successfully", content = @Content(schema = @Schema(implementation = OrderResponseDTO.class)))
//    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = OrderErrorDTO.class)))

//    @GetMapping("/getOrderReady")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderReady () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderReady());
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//    }


//    @GetMapping("/getOrderDelivered")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderDelivered () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDelivered());
//
//        } catch (Exception e) {
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }


//    @GetMapping("/getOrderReady")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderReady () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderReady());
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//    }
//
//    @GetMapping("/getOrderDelivered")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderDelivered () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderDelivered());
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }
//
//    @GetMapping("/getOrderCanceled")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderCanceled () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderCanceled());
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//    }
//
//    @GetMapping("/getOrderPending")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderPending () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderPending());
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//    }
//
//    @GetMapping("getOrderPreparation")
//    public ResponseEntity<List<OrderResponseDTO>> getOrderPreparation () {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderPreparation());
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }
    }
