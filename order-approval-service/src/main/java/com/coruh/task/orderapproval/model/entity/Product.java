package com.coruh.task.orderapproval.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "T_PRODUCT")
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String productName;
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Package> packageList;
    private boolean needsConfiguration;
}
