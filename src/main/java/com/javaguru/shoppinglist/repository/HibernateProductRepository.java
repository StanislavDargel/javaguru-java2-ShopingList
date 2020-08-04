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
        ProductEntity entity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
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
        ProductEntity entity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public Optional<ProductEntity> changeProductParameters(Long id, ProductEntity productEntity) {
        sessionFactory.getCurrentSession().update(productEntity);
        ProductEntity entity = (ProductEntity) sessionFactory.getCurrentSession().createCriteria(ProductEntity.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(entity);
    }
}
