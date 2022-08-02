package com.capgemini.Logistics.orders.model;

import com.capgemini.Logistics.orders.OrderStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private Integer orderId;
    private LocalDate deliveryDate;
    private LocalDate lastUpdated;
    private OrderStatus orderStatus;
    private String destination;
}
