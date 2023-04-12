package com.coruh.task.product;


import com.coruh.task.product.model.entity.Product;
import com.coruh.task.product.model.entity.ProductPackage;
import com.coruh.task.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductServiceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
           /*Product product = new Product();
            product.setProductName("Mobile Phone");
            product.setNeedsConfiguration(false);
            ProductPackage productPackage = new ProductPackage();
            productPackage.setProduct(product);
            productPackage.setPackageName("Prepaid");
            List<ProductPackage> productPackageList = new ArrayList<>();
            productPackageList.add(productPackage);
            product.setPackageList(productPackageList);
            productRepository.save(product);*/

            /*Product product = new Product();
            product.setProductName("Television");
            product.setNeedsConfiguration(true);
            ProductPackage productPackage = new ProductPackage();
            productPackage.setProduct(product);
            productPackage.setPackageName("With Internet Package");
            List<ProductPackage> productPackageList = new ArrayList<>();
            productPackageList.add(productPackage);
            product.setPackageList(productPackageList);
            productRepository.save(product);*/

    }
}
