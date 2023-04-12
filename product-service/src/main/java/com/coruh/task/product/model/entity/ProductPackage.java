package com.coruh.task.product.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "T_PRODUCT_PACKAGE",uniqueConstraints={@UniqueConstraint(name="UniqueProductPackage",
        columnNames={"packageName","product_id"})})
public class ProductPackage {
    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String packageName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}