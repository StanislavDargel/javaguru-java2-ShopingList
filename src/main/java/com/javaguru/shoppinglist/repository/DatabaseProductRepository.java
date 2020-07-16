package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile("mysql")
public class DatabaseProductRepository implements ProductRepository {
    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return null;
    }

    @Override
    public Optional<ProductEntity> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductEntity> deleteProduct(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductEntity> changeProductParameters(Long id, ProductEntity productEntity) {
        return Optional.empty();
    }
}
