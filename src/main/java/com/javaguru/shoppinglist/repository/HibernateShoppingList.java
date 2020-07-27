package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Profile("hibernate")
public class HibernateShoppingList implements ProductRepository {
    private final SessionFactory sessionFactory;

    public HibernateShoppingList(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public ProductEntity save(ProductEntity productEntity) {
        sessionFactory.getCurrentSession().save(productEntity);
        return productEntity;
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
    public void deleteProduct(Long id) {

    }

    @Override
    public Optional<ProductEntity> changeProductParameters(Long id, ProductEntity productEntity) {
        return Optional.empty();
    }
}
