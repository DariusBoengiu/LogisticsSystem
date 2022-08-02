package com.capgemini.Logistics.orders.model;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.orders.OrderStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    private Long lastUpdated;

    @ManyToOne
    @JoinColumn(name="destination_id", nullable=false)
    private Destination destination;


}
