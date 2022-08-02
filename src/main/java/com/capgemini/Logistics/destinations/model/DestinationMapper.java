package com.capgemini.Logistics.destinations.model;

import org.mapstruct.Mapper;

public class DestinationMapper {

    public static DestinationDTO toDestinationDTO(Destination destination) {
        return DestinationDTO.builder()
                .destinationId(destination.getDestinationId())
                .destinationName(destination.getDestinationName())
                .distance(destination.getDistance())
                .build();
    }

    public static Destination toDestinationEntity(DestinationDTO destinationDTO) {
        return Destination.builder()
                .destinationId(destinationDTO.getDestinationId())
                .destinationName(destinationDTO.getDestinationName())
                .distance(destinationDTO.getDistance())
                .build();

    }

}
