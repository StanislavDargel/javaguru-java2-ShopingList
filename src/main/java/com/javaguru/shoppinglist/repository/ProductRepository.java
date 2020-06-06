package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;

import java.util.Optional;

public interface ProductRepository {
    ProductEntity save(ProductEntity productEntity);

    Optional<ProductEntity> findProductById(Long id);

    Optional<ProductEntity> findProductByName(String name);

    Optional<ProductEntity> deleteProduct(Long id);

    Optional<ProductEntity> changeProductParameters(Long id, ProductEntity productEntity);


}