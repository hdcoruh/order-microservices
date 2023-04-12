package com.coruh.task.orderapproval.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "T_PACKAGE")
public class Package {
    @Id
    @GeneratedValue
    private UUID id;
    private String packageName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
