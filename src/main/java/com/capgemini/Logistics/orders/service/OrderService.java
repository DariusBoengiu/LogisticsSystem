package com.capgemini.Logistics.orders.service;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.destinations.repository.DestinationRepository;
import com.capgemini.Logistics.exceptions.NoncompliantDateException;
import com.capgemini.Logistics.orders.model.Order;
import com.capgemini.Logistics.orders.model.OrderDTO;
import com.capgemini.Logistics.orders.model.OrderMapper;
import com.capgemini.Logistics.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinylog.Logger;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final LocalDate APPLICATION_DATE = LocalDate.of(2022, 1, 1);

    private final OrderRepository orderRepository;
    private final DestinationRepository destinationRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, DestinationRepository destinationRepository) {
        this.orderRepository = orderRepository;
        this.destinationRepository = destinationRepository;
    }

    public Order createOrderEntityFromMinimalData(String deliveryDate, String destination) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(deliveryDate, formatter);

        Long dateCreated = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Destination destinationEntity = destinationRepository.findDestinationByDestinationName(destination).get();
        return new Order(destinationEntity, dateCreated);
    }

    @Transactional()
    public List<OrderDTO> addOrders(OrderDTO[] orders) {
        List<Order> ordersToBeAdded = new ArrayList<>();

        for (OrderDTO order : orders) {
            ordersToBeAdded.add(createOrderEntityFromMinimalData(order.getDeliveryDate(), order.getDestination()));
        }

        for (Order order : ordersToBeAdded) {
            if (order.getDeliveryDate() < APPLICATION_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()) {
                throw new NoncompliantDateException("Provided delivery date is before application current date");
            }
        }

        List<Order> savedOrders = orderRepository.saveAll(ordersToBeAdded);

        return savedOrders.stream().map(OrderMapper::toOrderDTO).collect(Collectors.toList());
    }
}
