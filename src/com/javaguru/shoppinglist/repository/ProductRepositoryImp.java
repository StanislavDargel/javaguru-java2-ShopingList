package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryImp implements ProductRepository {
    private static volatile ProductRepositoryImp repository;
    private static final Map<String, Product> inMemoryDatabase = new HashMap<>();
    private static Long productIdSequence = 0L;

    private ProductRepositoryImp() {

    }

    public static ProductRepositoryImp getRepository() {
        if (repository == null) {
            synchronized (ProductRepositoryImp.class) {
                if (repository == null) {
                    repository = new ProductRepositoryImp();
                }
            }
        }
        return repository;
    }

    @Override
    public Product save(Product product) {
        product.setId(productIdSequence);
        inMemoryDatabase.put(product.getName(), product);
        productIdSequence++;
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        for (Map.Entry<String, Product> pair : inMemoryDatabase.entrySet()) {
            if (pair.getValue().getId().equals(id)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        for (Map.Entry<String, Product> pair : inMemoryDatabase.entrySet()) {
            if (pair.getValue().getId().equals(id)) {
                inMemoryDatabase.remove(pair.getKey());
            }
        }
        return findProductById(id);
    }

    @Override
    public Product changeProductParameters(Long id, Product product) {
        for (Map.Entry<String, Product> pair : inMemoryDatabase.entrySet()) {
            if (pair.getValue().getId().equals(id) && !pair.getKey().equals(product.getName())) {
                inMemoryDatabase.remove(pair.getKey());
                inMemoryDatabase.put(product.getName(), product);
                return inMemoryDatabase.get(product.getName());
            }
        }
        return inMemoryDatabase.put(product.getName(), product);
    }
}