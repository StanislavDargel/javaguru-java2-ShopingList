package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotFoundException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.ValidationExceptionMessages;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductValidationService productValidationService;
    private final BeanMapper beanMapper;

    public ProductService(ProductRepository productRepository,
                          ProductValidationService productValidationService,
                          BeanMapper beanMapper) {
        this.repository = productRepository;
        this.productValidationService = productValidationService;
        this.beanMapper = beanMapper;
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        productValidationService.validateProduct(productDTO);
        ProductEntity entity = beanMapper.toProductEntity(productDTO);
        ProductEntity output = repository.save(entity);
        return beanMapper.toProductDTO(output);
    }

    public ProductDTO findById(Long id) {
        ProductEntity entity = repository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE));
        return beanMapper.toProductDTO(entity);
    }

    public void removeProduct(Long id) {
        repository.deleteProduct(id);
    }

    public ProductDTO changeParameters(Long id, ProductDTO productDTO) {
        ProductEntity entity = beanMapper.toProductEntity(productDTO);
        ProductEntity changedProduct = repository.changeProductParameters(id, entity)
                .orElseThrow(() -> new ProductNotFoundException(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE));
        return beanMapper.toProductDTO(changedProduct);
    }
}