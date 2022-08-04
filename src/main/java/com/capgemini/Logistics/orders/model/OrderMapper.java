package com.capgemini.Logistics.orders.model;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.destinations.model.DestinationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class OrderMapper {

    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .deliveryDate(Instant.ofEpochMilli(order.getDeliveryDate()).atZone(ZoneId.systemDefault()).toLocalDate().toString())
                .lastUpdated(Instant.ofEpochMilli(order.getLastUpdated()).atZone(ZoneId.systemDefault()).toLocalDate().toString())
                .destination(order.getDestination().getDestinationName())
                .build();
    }

    public static Order toOrderEntity(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .deliveryDate(
                        LocalDate.parse(orderDTO.getDeliveryDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")).
                                atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                )
                .destination(new Destination(orderDTO.getDestination()))
                .build();

    }
}
