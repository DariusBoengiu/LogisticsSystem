package com.capgemini.Logistics.destinations.repository;

import com.capgemini.Logistics.destinations.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
}
