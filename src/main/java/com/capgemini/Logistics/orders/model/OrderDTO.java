package com.capgemini.Logistics.orders.model;

import com.capgemini.Logistics.orders.OrderStatus;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private Integer orderId;
    @NotNull
    private String deliveryDate;
    private String lastUpdated;
    private OrderStatus orderStatus;
    @NotNull
    private String destination;
}
