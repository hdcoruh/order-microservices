package com.coruh.task.product.repository;

import com.coruh.task.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("select p from Product p, ProductPackage pp where p.productName = :productName " +
            "and p.id = pp.product and pp.packageName = :packageName")
    Optional<Product> findByProductNameAndPackageListContainsPackageName(@Param("productName") String productName,
                                                                @Param("packageName") String packageName);

}