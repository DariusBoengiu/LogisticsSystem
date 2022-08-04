package com.capgemini.Logistics.orders.controller;

import com.capgemini.Logistics.orders.model.OrderDTO;
import com.capgemini.Logistics.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<List<OrderDTO>> addOrders(@RequestBody @Valid OrderDTO[] orders) {
        return new ResponseEntity<>(orderService.addOrders(orders), HttpStatus.CREATED);
    }
}
