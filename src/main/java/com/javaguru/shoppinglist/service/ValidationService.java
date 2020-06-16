package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotFoundException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.ValidationExceptionMessages;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.String.format;

@Service
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
        productValidationService.validateProduct(productDTO);
        ProductEntity entity = beanMapper.toProductEntity(productDTO);
        repository.save(entity);
        ProductDTO dto = beanMapper.toProductDTO(entity);
        return dataNormalizer(dto);
    }

    public ProductDTO findById(Long id) {
        ProductEntity entity = repository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE));
        ProductDTO dto = beanMapper.toProductDTO(entity);
        return dataNormalizer(dto);
    }

    public ProductDTO removeProduct(Long id) {
        ProductEntity removableProduct = repository.deleteProduct(id)
                .orElseThrow(() -> new ProductNotFoundException(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE));
        ProductDTO dto = beanMapper.toProductDTO(removableProduct);
        return dataNormalizer(dto);
    }

    public ProductDTO changeParameters(Long id, ProductDTO productDTO) {
        ProductEntity entity = beanMapper.toProductEntity(productDTO);
        ProductEntity changedProduct = repository.changeProductParameters(id, entity)
                .orElseThrow(() -> new ProductNotFoundException(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE));
        ProductDTO dto = beanMapper.toProductDTO(changedProduct);
        return dataNormalizer(dto);
    }

    private ProductDTO dataNormalizer(ProductDTO productDTO) {
        productDTO.setName(productDTO.getName().substring(0, 1).toUpperCase() + productDTO.getName().substring(1).toLowerCase());
        productDTO.setPrice(productDTO.getPrice().setScale(2, RoundingMode.HALF_EVEN));
        if (productDTO.getPrice().compareTo(new BigDecimal("20.00")) >= 0 && productDTO.getDiscount() != null) {
            productDTO.setDiscount(productDTO.getDiscount().setScale(1, RoundingMode.FLOOR));
            productDTO.setActualPrice(productDTO.getPrice().subtract(productDTO.getPrice().movePointLeft(2).multiply(productDTO.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
        } else {
            productDTO.setDiscount(BigDecimal.ZERO);
        }
        return productDTO;
    }

    public String printProductInfo(ProductDTO productDTO) {
        String info = format("\nName: %s\nID: %d\nPrice: %.2f\nCategory: %s%n",
                productDTO.getName(), productDTO.getId(), productDTO.getPrice(), productDTO.getCategory());
        if (productDTO.getDescription() != null && !productDTO.getDescription().isEmpty()) {
            info += format("Description: %s%n", productDTO.getDescription());
        }
        if (productDTO.getDiscount() != null && productDTO.getDiscount().compareTo(BigDecimal.ZERO) > 0) {
            info += format("Discount: %.1f %% \nActual Price: %.2f%n",
                    productDTO.getDiscount(), productDTO.getActualPrice());
        }
        return info;
    }
}