package com.coruh.task.product.controller;

import com.coruh.task.product.model.dto.ProductResponse;
import com.coruh.task.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@RequestParam String productName,
                                      @RequestParam String packageName) {
        return productService.getProduct(productName,packageName);
    }
}
