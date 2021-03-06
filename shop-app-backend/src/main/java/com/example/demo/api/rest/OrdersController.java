package com.example.demo.api.rest;

import com.example.demo.core.models.OrdersEntity;
import com.example.demo.core.services.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(allowedHeaders = {
        "Access-Control-Allow-Origin",
        "Access-Control-Allow-Headers",
        "Access-Control-Allow-Methods",
        "Content-Type"
},
        methods = { RequestMethod.POST,RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<List<OrdersEntity>> getUserOrders(@PathVariable(name = "id")UUID id) {
        var result = ordersService.getAllByID(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OrdersEntity> generateOrder(@RequestParam(name = "orderType")String orderType) {
        OrdersEntity result = ordersService.generateOrder(orderType);
        return ResponseEntity.ok(result);
    }

}
