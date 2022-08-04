package com.capgemini.Logistics.orders;

import com.capgemini.Logistics.orders.model.Order;
import com.capgemini.Logistics.orders.repository.OrderRepository;
import com.capgemini.Logistics.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class AddDataToOrders {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @Autowired
    public AddDataToOrders(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void addOrders() {

        try (BufferedReader br = new BufferedReader(
                new FileReader("src/main/resources/order.csv"))) {
            String line ;
            while ((line = br.readLine()) != null) {
                extractOrder(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractOrder(String line) {
        try {
            orderRepository.save(getOrderFromCsvLine(line));
        } catch (IllegalArgumentException e) {
            Logger.error(e.getMessage());
        }
    }

    protected Order getOrderFromCsvLine(String line) {

        String[] orderAttributes = line.split(",");
        if (orderAttributes.length != 2) {
            throw new IllegalArgumentException("Corrupted data");
        }
        return orderService.createOrderEntityFromMinimalData(orderAttributes[1], orderAttributes[0]);
    }
}
