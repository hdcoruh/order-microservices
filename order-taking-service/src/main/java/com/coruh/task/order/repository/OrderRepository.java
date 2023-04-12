package com.coruh.task.order.repository;

import com.coruh.task.order.model.entity.Order;
import com.coruh.task.order.model.entity.InstallationSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findByInstallationDateAndInstallationSlot(LocalDate installationDate,
                                                              InstallationSlot installationSlot);
}
