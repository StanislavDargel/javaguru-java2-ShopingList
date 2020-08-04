package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCartEntity;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository {
    ShoppingCartEntity save(ShoppingCartEntity shoppingCartEntity);

    Optional<ShoppingCartEntity> findById(long id);

    List<ShoppingCartEntity> findAll();

    void update(ShoppingCartEntity shoppingCartEntity);
}
