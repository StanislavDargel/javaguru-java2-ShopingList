package com.javaguru.shoppinglist;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private static volatile ProductRepository repository;
    private static final Map<Long, Product> inMemoryDatabase = new HashMap<>();
    private static Long productIdSequence = 0L;

    private ProductRepository() {

    }

    public static ProductRepository getRepository() {
        if (repository == null) {
            synchronized (ProductRepository.class) {
                if (repository == null) {
                    repository = new ProductRepository();
                }
            }
        }
        return repository;
    }

    public long getDatabaseSize() {
        return inMemoryDatabase.size();
    }

    public Product save(Product product) {
        product.setId(productIdSequence);
        inMemoryDatabase.put(product.getId(), product);
        productIdSequence++;
        return product;
    }

    public Product findProductById(Long id) {
        return inMemoryDatabase.get(id);
    }

    public Product deleteProduct(Long id) {
        inMemoryDatabase.remove(id);
        return inMemoryDatabase.get(id);
    }

    public Product changeProductParameters(Long id, Product product) {
        return inMemoryDatabase.put(id, product);
    }

}
