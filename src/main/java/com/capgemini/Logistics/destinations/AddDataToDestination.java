package com.capgemini.Logistics.destinations;

import com.capgemini.Logistics.destinations.model.Destination;
import com.capgemini.Logistics.destinations.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class AddDataToDestination {

    private final DestinationRepository destinationRepository;

    @Autowired
    public AddDataToDestination(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void addDestinations() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/main/resources/destination.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                extractDestination(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractDestination(String line) {
        try {
            destinationRepository.save(getDestinationFromCsvLine(line));
        } catch (IllegalArgumentException e) {
            Logger.error(e.getMessage());
        }
    }

    protected Destination getDestinationFromCsvLine(String line) {
        String[] destinationAttributes = line.split(",");

        if (destinationAttributes.length != 2) {
            throw new IllegalArgumentException("Corrupted data");
        }
        Integer distance = Integer.parseInt(destinationAttributes[1].trim());
        return new Destination(destinationAttributes[0].trim(),
                distance);
    }
}
