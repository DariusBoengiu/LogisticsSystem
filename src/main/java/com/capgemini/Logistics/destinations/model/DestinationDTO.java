package com.capgemini.Logistics.destinations.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class DestinationDTO {

    private Integer destinationId;
    private String destinationName;
    private Integer distance;
}
