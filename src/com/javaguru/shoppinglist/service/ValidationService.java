package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepositoryImp;
import com.javaguru.shoppinglist.service.validation.ProductNotFoundException;
import com.javaguru.shoppinglist.service.validation.ProductNotRemovedException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class ValidationService {
    private static volatile ValidationService service;

    private ValidationService() {
    }

    public static ValidationService getService() {
        if (service == null) {
            synchronized (ValidationService.class) {
                if (service == null) {
                    service = new ValidationService();
                }
            }
        }
        return service;
    }

    public Product saveProduct(Product product) {
        ProductValidationService.getProductService().validateProduct(product);
        return ProductRepositoryImp.getRepository().save(product);
    }

    public Product findById(Long id) {
        if (ProductRepositoryImp.getRepository().findProductById(id) == null) {
            throw new ProductNotFoundException("Product with ID " + id + " is not founded");
        }
        return ProductRepositoryImp.getRepository().findProductById(id);
    }

    public Product removeProduct(Long id) {
        if (ProductRepositoryImp.getRepository().deleteProduct(id) != null) {
            throw new ProductNotRemovedException("Product with id " + id + " not deleted");
        }
        return ProductRepositoryImp.getRepository().findProductById(id);
    }

    public Product changeParameters(Long id, Product product) {
        ProductValidationService.getProductService().validateProduct(product);
        return ProductRepositoryImp.getRepository().changeProductParameters(id, product);
    }
}