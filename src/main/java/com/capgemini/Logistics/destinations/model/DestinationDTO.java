package com.capgemini.Logistics.destinations.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class DestinationDTO {

    private Integer destinationId;
    
    private String destinationName;
    @NotNull
    private Integer distance;
}
