package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.domain.ShoppingCartEntity;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCartEntity save(ShoppingCartEntity shoppingCartEntity) {
        return shoppingCartRepository.save(shoppingCartEntity);
    }

    public ShoppingCartEntity findSoppingCartById(long id) {
        return shoppingCartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Shopping cart with id: " + id + "doesn't exist"));
    }

    public List<ShoppingCartEntity> findAllCarts() {
        return shoppingCartRepository.findAll();
    }

    public void addProductToShoppingCart(long shoppingCartId, ProductEntity productEntity) {
        ShoppingCartEntity shoppingCart = shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new IllegalArgumentException("Shopping cart with " + shoppingCartId + " doesn't exist"));
        productEntity.setShoppingCart(shoppingCart);
        shoppingCartRepository.update(productEntity);
    }

    private Optional<ShoppingCartEntity> findById(long id) {
        return shoppingCartRepository.findById(id);
    }
}
