package com.capgemini.Logistics.destinations.service;

import com.capgemini.Logistics.destinations.model.DestinationDTO;
import com.capgemini.Logistics.destinations.model.DestinationMapper;
import com.capgemini.Logistics.destinations.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    public List<DestinationDTO> getAllDestinations() {
        return destinationRepository.findAll().stream().map(DestinationMapper::toDestinationDTO).collect(Collectors.toList());
    }
}
