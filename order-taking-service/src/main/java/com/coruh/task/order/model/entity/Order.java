package com.coruh.task.order.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table( name = "T_ORDER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private String packageName;
    private String productName;
    private String customerName;
    private String customerEmail;
    private String address;
    private LocalDate installationDate;
    @Enumerated(EnumType.STRING)
    private InstallationSlot installationSlot;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}