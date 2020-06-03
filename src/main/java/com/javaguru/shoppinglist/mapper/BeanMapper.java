package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;

public class BeanMapper {

    public ProductDTO toProductDTO(ProductEntity productEntity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(productEntity.getId());
        dto.setCategory(productEntity.getCategory());
        dto.setName(productEntity.getName());
        dto.setPrice(productEntity.getPrice());
        dto.setDiscount(productEntity.getDiscount());
        dto.setDescription(productEntity.getDescription());
        return dto;
    }

    public ProductEntity toProductEntity(ProductDTO productDTO) {
        ProductEntity entity = new ProductEntity();
        entity.setId(productDTO.getId());
        entity.setName(productDTO.getName());
        entity.setCategory(productDTO.getCategory());
        entity.setPrice(productDTO.getPrice());
        entity.setDiscount(productDTO.getDiscount());
        entity.setDescription(productDTO.getDescription());
        return entity;
    }
}
