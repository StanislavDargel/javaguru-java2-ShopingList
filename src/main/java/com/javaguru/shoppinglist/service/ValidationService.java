package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotFoundException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ValidationService {
    private final ProductRepository repository;
    private final ProductValidationService productValidationService;
    private final BeanMapper beanMapper;

    public ValidationService(ProductRepository productRepository, ProductValidationService productValidationService, BeanMapper beanMapper) {
        this.repository = productRepository;
        this.productValidationService = productValidationService;
        this.beanMapper = beanMapper;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        this.productValidationService.validateProduct(productDTO);
        ProductEntity entity = this.repository.save(this.beanMapper.toProductEntity(productDTO));
        return dataNormalizer(this.beanMapper.toProductDTO(entity));
    }

    public ProductDTO findById(Long id) {
        if (this.repository.findProductById(id) == null) {
            throw new ProductNotFoundException("Product with ID " + id + " doesn't exist");
        }
        return dataNormalizer(this.beanMapper.toProductDTO(this.repository.findProductById(id)));
    }

    public ProductDTO removeProduct(Long id) {
        return dataNormalizer(this.beanMapper.toProductDTO(this.repository.deleteProduct(id)));
    }

    public ProductDTO changeParameters(Long id, ProductDTO productDTO) {
        if (this.repository.findProductById(id) == null) {
            throw new ProductNotFoundException("Product with ID " + id + " doesn't exist");
        }
        this.productValidationService.validateProduct(productDTO);
        return dataNormalizer(this.beanMapper.toProductDTO(
                this.repository.changeProductParameters(id, this.beanMapper.toProductEntity(productDTO))));
    }

    private ProductDTO dataNormalizer(ProductDTO productDTO) {
        productDTO.setName(productDTO.getName().substring(0, 1).toUpperCase() + productDTO.getName().substring(1).toLowerCase());
        productDTO.setPrice(productDTO.getPrice().setScale(2, RoundingMode.HALF_EVEN));
        if (productDTO.getPrice().compareTo(new BigDecimal(20.00)) >= 0 && productDTO.getDiscount() != null) {
            productDTO.setDiscount(productDTO.getDiscount().setScale(1, RoundingMode.FLOOR));
            productDTO.setActualPrice(productDTO.getPrice().subtract(productDTO.getPrice().movePointLeft(2).multiply(productDTO.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
        } else {
            productDTO.setDiscount(BigDecimal.ZERO);
        }
        return productDTO;
    }

    public void printProductInfo(ProductDTO productDTO) {
        System.out.println(String.format("\nName: %s\nID: %s\nPrice: %s\nCategory: %s",
                productDTO.getName(), productDTO.getId(), productDTO.getPrice(), productDTO.getCategory()));
        if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
            System.out.println(String.format("Description: %s", productDTO.getDescription()));
        }
        if (productDTO.getDiscount() != null && productDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            System.out.println(String.format("Discount: %s %% \nActual Price: %s",
                    productDTO.getDiscount(), productDTO.getActualPrice()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationService)) return false;
        ValidationService that = (ValidationService) o;
        return Objects.equals(repository, that.repository) &&
                Objects.equals(productValidationService, that.productValidationService) &&
                Objects.equals(beanMapper, that.beanMapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository, productValidationService, beanMapper);
    }

    @Override
    public String toString() {
        return "ValidationService{" +
                "repository=" + repository +
                ", productValidationService=" + productValidationService +
                ", beanMapper=" + beanMapper +
                '}';
    }
}