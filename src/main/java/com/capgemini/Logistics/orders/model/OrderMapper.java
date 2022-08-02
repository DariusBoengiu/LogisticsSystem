package com.capgemini.Logistics.orders.model;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.destinations.model.DestinationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.ZoneId;


public class OrderMapper {

    public static OrderDTO toOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .deliveryDate(Instant.ofEpochMilli(order.getDeliveryDate()).atZone(ZoneId.systemDefault()).toLocalDate())
                .lastUpdated(Instant.ofEpochMilli(order.getLastUpdated()).atZone(ZoneId.systemDefault()).toLocalDate())
                .build();
    }

    public static Order toOrderEntity(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .deliveryDate(orderDTO.getDeliveryDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .destination(new Destination(orderDTO.getDestination()))
                .build();

    }
}
