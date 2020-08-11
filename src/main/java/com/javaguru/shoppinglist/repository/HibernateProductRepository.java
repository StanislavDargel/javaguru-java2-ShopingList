package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Profile("hibernate")
@Transactional
public class HibernateProductRepository implements ProductRepository {
    private final SessionFactory sessionFactory;

    public HibernateProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        sessionFactory.getCurrentSession().save(productEntity);
        return productEntity;
    }

    @Override
    public Optional<ProductEntity> findProductById(Long id) {
        ProductEntity entity = sessionFactory.getCurrentSession().find(ProductEntity.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        ProductEntity entity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return Optional.ofNullable(entity);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity productEntity = sessionFactory.getCurrentSession().find(ProductEntity.class, id);
        productEntity.getShoppingCarts().forEach(shoppingCart ->
                shoppingCart.getProducts().remove(productEntity));
        sessionFactory.getCurrentSession().remove(productEntity);
    }

    @Override
    public void updateProduct(ProductEntity productEntity) {
        sessionFactory.getCurrentSession().update(productEntity);
    }
}
