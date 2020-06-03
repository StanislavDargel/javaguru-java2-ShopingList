package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, ProductEntity> inMemoryDatabase = new HashMap<>();
    private Long productIdSequence = 0L;

    public ProductRepositoryImpl() {
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        for (Map.Entry<Long, ProductEntity> pair : inMemoryDatabase.entrySet()) {
            if (pair.getValue().getName().equalsIgnoreCase(productEntity.getName())) {
                productEntity.setId(pair.getValue().getId());
                return inMemoryDatabase.put(pair.getKey(), productEntity);
            }
        }
        productEntity.setId(productIdSequence);
        inMemoryDatabase.put(productEntity.getId(), productEntity);
        productIdSequence++;
        return productEntity;
    }

    @Override
    public ProductEntity findProductById(Long id) {
        return inMemoryDatabase.get(id);
    }

    @Override
    public ProductEntity deleteProduct(Long id) {
        return inMemoryDatabase.remove(id);
    }

    @Override
    public ProductEntity changeProductParameters(Long id, ProductEntity productEntity) {
        if (inMemoryDatabase.containsKey(id)) {
            inMemoryDatabase.put(id, productEntity);
        }
        return inMemoryDatabase.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRepositoryImpl)) return false;
        ProductRepositoryImpl that = (ProductRepositoryImpl) o;
        return Objects.equals(inMemoryDatabase, that.inMemoryDatabase) &&
                Objects.equals(productIdSequence, that.productIdSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inMemoryDatabase, productIdSequence);
    }

    @Override
    public String toString() {
        return "ProductRepositoryImpl{" +
                "inMemoryDatabase=" + inMemoryDatabase +
                ", productIdSequence=" + productIdSequence +
                '}';
    }
}