package com.coruh.task.orderapproval.repository;

import com.coruh.task.orderapproval.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
