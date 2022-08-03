package com.capgemini.Logistics.destinations.controller;

import com.capgemini.Logistics.destinations.model.DestinationDTO;
import com.capgemini.Logistics.destinations.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/destination")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
        return new ResponseEntity<>(destinationService.getAllDestinations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDTO> getDestinationById(@PathVariable Integer id) {
        return new ResponseEntity<>(destinationService.getDestinationById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDestinationById(@PathVariable Integer id) {
        destinationService.deleteDestinationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<DestinationDTO> addDestination(@RequestBody @Valid DestinationDTO destinationDTO) {
        return new ResponseEntity<>(destinationService.addDestination(destinationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationDTO> updateDestination(@PathVariable Integer id,
                                                            @RequestBody @Valid DestinationDTO destinationDTO) {
        return new ResponseEntity<>(destinationService.updateDestination(id, destinationDTO), HttpStatus.OK);
    }
}
