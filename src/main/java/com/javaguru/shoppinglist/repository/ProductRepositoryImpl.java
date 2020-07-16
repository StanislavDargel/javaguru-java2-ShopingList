package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("inmemory")
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, ProductEntity> inMemoryDatabase = new HashMap<>();
    private Long productIdSequence = 0L;

    public ProductRepositoryImpl() {
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        productEntity.setId(productIdSequence);
        inMemoryDatabase.put(productEntity.getId(), productEntity);
        productIdSequence++;
        return productEntity;
    }

    @Override
    public Optional<ProductEntity> findProductById(Long id) {
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        return inMemoryDatabase.values().stream()
                .filter(productEntity -> productEntity.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public Optional<ProductEntity> deleteProduct(Long id) {
        return Optional.ofNullable(inMemoryDatabase.remove(id));
    }

    @Override
    public Optional<ProductEntity> changeProductParameters(Long id, ProductEntity productEntity) {
        if (inMemoryDatabase.containsKey(id)) {
            inMemoryDatabase.put(id, productEntity);
        }
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }
}