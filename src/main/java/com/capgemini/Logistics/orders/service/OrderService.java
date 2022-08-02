package com.capgemini.Logistics.orders.service;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.destinations.repository.DestinationRepository;
import com.capgemini.Logistics.orders.model.Order;
import com.capgemini.Logistics.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DestinationRepository destinationRepository;

    public OrderService(OrderRepository orderRepository, DestinationRepository destinationRepository) {
        this.orderRepository = orderRepository;
        this.destinationRepository = destinationRepository;
    }

    public Order createEntityFromMinimalData(String deliveryDate, String destination) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate localDate = LocalDate.parse(deliveryDate, formatter);

        Long dateCreated = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Destination destinationEntity = destinationRepository.findDestinationByDestinationName(destination).get();
        return new Order(destinationEntity, dateCreated);
    }
}
