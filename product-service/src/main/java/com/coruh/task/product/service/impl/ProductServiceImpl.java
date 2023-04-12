package com.coruh.task.product.service.impl;

import com.coruh.task.product.model.dto.ProductResponse;
import com.coruh.task.product.model.entity.Product;
import com.coruh.task.product.repository.ProductRepository;
import com.coruh.task.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse getProduct(String productName, String packageName) {
        return productRepository.findByProductNameAndPackageListContainsPackageName(productName,packageName)
                .map( p -> map(p,packageName))
                .orElseThrow( () -> new EntityNotFoundException("Product"));

    }

    private ProductResponse map(Product product,String packageName){
        return  ProductResponse.builder()
                    .id(product.getId())
                    .productName(product.getProductName())
                    .packageName(packageName)
                    .isNeedsConfiguration(product.isNeedsConfiguration())
                    .build();
    }
}
