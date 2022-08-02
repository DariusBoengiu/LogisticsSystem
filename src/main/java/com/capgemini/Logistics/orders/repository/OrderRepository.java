package com.capgemini.Logistics.orders.repository;

import com.capgemini.Logistics.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
