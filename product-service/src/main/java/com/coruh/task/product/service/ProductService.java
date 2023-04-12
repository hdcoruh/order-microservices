package com.coruh.task.product.service;

import com.coruh.task.product.model.dto.ProductResponse;

public interface ProductService {
    ProductResponse getProduct(String productName, String packageName);
}
