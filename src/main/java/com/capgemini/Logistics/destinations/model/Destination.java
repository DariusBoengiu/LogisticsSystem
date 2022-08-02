package com.capgemini.Logistics.destinations.model;

import com.capgemini.Logistics.orders.model.Order;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "destinations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Integer destinationId;

    @Column(name = "destination_name", unique = true)
    private String destinationName;

    private Integer distance;

    @OneToMany(mappedBy="destination")
    private List<Order> orders;

    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }
}
