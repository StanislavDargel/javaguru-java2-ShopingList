package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductValidationService {
    private static volatile ProductValidationService productService;

    private ProductValidationService() {

    }

    public static ProductValidationService getProductService() {
        if (productService == null) {
            synchronized (ProductValidationService.class) {
                if (productService == null) {
                    productService = new ProductValidationService();
                }
            }
        }
        return productService;
    }

    public void validate(Product product) {
        productNotNull(product);
        productNameNotEmpty(product);
        productPriceNotNullOrNotNegative(product);
        productCategoryNotNull(product);
        if (product.getDiscount() != null) productDiscountIsCorrect(product);
        if (product.getDescription() != null) productDescriptionNotEmpty(product);
    }

    private void productNotNull(Product product) {
        if (product == null) throw new ProductValidationExceptions("Product must be not null");
    }

    private void productNameNotEmpty(Product product) {
        if (product.getName() == null || product.getName().isEmpty())
            throw new ProductValidationExceptions("Product name must be not empty");
        else if (product.getName().length() < 3 || product.getName().length() > 32)
            throw new ProductValidationExceptions("Product name length is incorrect (3 - 32 letters)");
        else
            product.setName(product.getName().substring(0, 1).toUpperCase() + product.getName().substring(1).toLowerCase());
    }

    private void productPriceNotNullOrNotNegative(Product product) {
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new ProductValidationExceptions("Product price is Incorrect, price must be more than ZERO");
        else product.setPrice(product.getPrice().setScale(2, RoundingMode.HALF_EVEN));
    }

    private void productCategoryNotNull(Product product) {
        if (product.getCategory() == null) throw new ProductValidationExceptions("Product category must be not null");
    }

    private void productDiscountIsCorrect(Product product) {
        if (product.getDiscount().compareTo(BigDecimal.ZERO) <= 0 || product.getDiscount().compareTo(BigDecimal.ONE) >= 0)
            throw new ProductValidationExceptions("Product discount is Incorrect, (Correct bounds (0,01 ~ 0,99))");
        else {
            product.setDiscount(product.getDiscount().setScale(2, RoundingMode.HALF_EVEN));
            product.setActualPrice(product.getPrice().subtract(product.getPrice().multiply(product.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
        }
    }

    private void productDescriptionNotEmpty(Product product) {
        if (product.getDescription().isEmpty())
            throw new ProductValidationExceptions("Product description must be not empty");
    }

    public void checkId(long id) {
        if (id < 0 || id > ProductRepository.getRepository().getDatabaseSize() - 1)
            throw new ProductValidationExceptions("Entered ID is incorrect");
    }

    public void checkFoundedProduct(Product product) {
        if (product == null) throw new ProductValidationExceptions("Product with such ID is not founded");
    }

    public void checkDeletedProduct(Product product) {
        if (product != null) throw new ProductValidationExceptions("Product not deleted");
    }



}
