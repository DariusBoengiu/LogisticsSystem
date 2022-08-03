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

    @Column(name = "destination_name", unique = true, nullable = false)
    private String destinationName;

    @Column(nullable = false)
    private Integer distance;

    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Order> orders;

    public Destination(String destinationName) {
        this.destinationName = destinationName;
    }

    public Destination(String destinationName, Integer distance) {
        this.destinationName = destinationName;
        this.distance = distance;
    }
}
