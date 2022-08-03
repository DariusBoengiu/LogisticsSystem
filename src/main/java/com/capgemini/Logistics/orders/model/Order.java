package com.capgemini.Logistics.orders.model;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.orders.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "delivery_date")
    private Long deliveryDate;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.NEW;

    @Column(name = "last_updated")
    private Long lastUpdated = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    public Order(Destination destination, Long deliveryDate) {
        this.destination = destination;
        this.deliveryDate = deliveryDate;
    }




}
