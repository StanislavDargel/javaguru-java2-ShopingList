package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.domain.ShoppingCartEntity;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCartEntity save(ShoppingCartEntity shoppingCartEntity) {
        return shoppingCartRepository.save(shoppingCartEntity);
    }

    public ShoppingCartEntity findSoppingCartById(long id) {
        return shoppingCartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Shopping cart with id: " + id + " doesn't exist"));
    }

    public List<ShoppingCartEntity> findAllCarts() {
        return shoppingCartRepository.findAll();
    }

    public void addProductToShoppingCart(long shoppingCartId, long productEntityId) {
        ShoppingCartEntity shoppingCart = findSoppingCartById(shoppingCartId);
        ProductEntity entity = productRepository.findProductById(productEntityId)
                .orElseThrow(() -> new IllegalArgumentException("Product with " + productEntityId + " doesn't exist"));
        shoppingCart.getProducts().add(entity);
        entity.getShoppingCarts().add(shoppingCart);
        productRepository.updateProduct(entity);
        shoppingCartRepository.update(shoppingCart);
    }

    public void removeShoppingCart(long id) {
        shoppingCartRepository.delete(id);
    }
}
