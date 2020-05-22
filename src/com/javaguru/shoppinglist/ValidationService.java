package com.javaguru.shoppinglist;

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
        ProductValidationService.getProductService().validate(product);
        return ProductRepository.getRepository().save(product);
    }

    public Product findById(Long id) {
        ProductValidationService.getProductService().checkId(id);
        ProductValidationService.getProductService().checkFoundedProduct(ProductRepository.getRepository().findProductById(id));
        return ProductRepository.getRepository().findProductById(id);
    }

    public Product removeProduct(Long id) {
        ProductValidationService.getProductService().checkId(id);
        ProductValidationService.getProductService().checkFoundedProduct(ProductRepository.getRepository().findProductById(id));
        Product removedProduct = ProductRepository.getRepository().deleteProduct(id);
        ProductValidationService.getProductService().checkDeletedProduct(removedProduct);
        return removedProduct;
    }

    public Product changeParameters(Long id, Product product) {
        ProductValidationService.getProductService().checkId(id);
        ProductValidationService.getProductService().validate(product);
        return ProductRepository.getRepository().changeProductParameters(id, product);
    }

}
