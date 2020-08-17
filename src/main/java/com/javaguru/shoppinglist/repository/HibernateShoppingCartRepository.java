package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCartEntity;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class HibernateShoppingCartRepository implements ShoppingCartRepository {
    private final SessionFactory sessionFactory;

    public HibernateShoppingCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCartEntity save(ShoppingCartEntity shoppingCartEntity) {
        sessionFactory.getCurrentSession().save(shoppingCartEntity);
        return shoppingCartEntity;
    }

    @Override
    public Optional<ShoppingCartEntity> findById(long id) {
        ShoppingCartEntity entity = sessionFactory.getCurrentSession().find(ShoppingCartEntity.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<ShoppingCartEntity> findAll() {
        return sessionFactory.getCurrentSession()
                .createCriteria(ShoppingCartEntity.class)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public void update(ShoppingCartEntity shoppingCartEntity) {
        sessionFactory.getCurrentSession().update(shoppingCartEntity);
    }

    @Override
    public void delete(long id) {
        ShoppingCartEntity shoppingCart = sessionFactory.getCurrentSession().find(ShoppingCartEntity.class, id);
        shoppingCart.getProducts().forEach(product -> {
            product.getShoppingCarts().remove(shoppingCart);
        });
        sessionFactory.getCurrentSession().remove(shoppingCart);
    }
}
