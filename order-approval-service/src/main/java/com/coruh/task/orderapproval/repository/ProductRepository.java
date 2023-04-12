package com.coruh.task.orderapproval.repository;

import com.coruh.task.orderapproval.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByProductNameAndPackageListContainingIgnoreCase(String productName, String packageName);

}
