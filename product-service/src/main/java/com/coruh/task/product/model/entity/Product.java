package com.coruh.task.product.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "T_PRODUCT",uniqueConstraints={@UniqueConstraint(columnNames={"productName"})})
public class Product {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String productName;
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductPackage> packageList;
    private boolean needsConfiguration;
}
