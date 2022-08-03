package com.capgemini.Logistics.destinations.service;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.destinations.model.DestinationDTO;
import com.capgemini.Logistics.destinations.model.DestinationMapper;
import com.capgemini.Logistics.destinations.repository.DestinationRepository;
import com.capgemini.Logistics.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    public List<DestinationDTO> getAllDestinations() {
        return destinationRepository.findAll().stream().map(DestinationMapper::toDestinationDTO).collect(Collectors.toList());
    }

    public DestinationDTO getDestinationById(Integer id) {
        return DestinationMapper.toDestinationDTO(
                destinationRepository.findById(id).
                        orElseThrow(
                                () -> new ResourceNotFoundException("Destination with requested ID does not exist")
                        )
        );
    }

    public void deleteDestinationById(Integer id) {
        getDestinationById(id);
        destinationRepository.deleteById(id);
    }

    @Transactional
    public DestinationDTO addDestination(DestinationDTO destinationDTO) {
        Destination destinationToSave = DestinationMapper.toDestinationEntity(destinationDTO);
        return DestinationMapper.toDestinationDTO(destinationRepository.save(destinationToSave));
    }

    public DestinationDTO updateDestination(Integer id, DestinationDTO destinationDTO) {
        Destination destinationToUpdate = DestinationMapper.toDestinationEntity(getDestinationById(id));
        copyNonNullProperties(destinationDTO, destinationToUpdate);
        return DestinationMapper.toDestinationDTO(destinationRepository.save(destinationToUpdate));
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
