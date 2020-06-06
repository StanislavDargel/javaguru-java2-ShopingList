package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BeanMapperTest {
    private BeanMapper victim = new BeanMapper();

    @Test
    public void shouldConvertProductEntityIntoProductDTO() {
        ProductEntity entity = new ProductEntity();
        entity.setName("VICTIM_ONE");
        entity.setId(10L);
        entity.setPrice(BigDecimal.ONE);
        entity.setCategory(ProductCategory.MEAT);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription("PRODUCT_DTO FOR TEST");
        ProductDTO actual = victim.toProductDTO(entity);

        assertNotNull(actual);
        assertEquals(dto(), actual);
    }

    @Test
    public void shouldConvertProductDTOintoProductEntity() {
        ProductDTO dto = new ProductDTO();
        dto.setName("VICTIM_TWO");
        dto.setId(10L);
        dto.setPrice(BigDecimal.ONE);
        dto.setCategory(ProductCategory.MEAT);
        dto.setDiscount(BigDecimal.TEN);
        dto.setDescription("PRODUCT_ENTITY FOR TEST");
        ProductEntity actual = victim.toProductEntity(dto);

        assertNotNull(actual);
        assertEquals(entity(), actual);
    }

    private ProductDTO dto() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("VICTIM_ONE");
        productDTO.setId(10L);
        productDTO.setPrice(BigDecimal.ONE);
        productDTO.setCategory(ProductCategory.MEAT);
        productDTO.setDiscount(BigDecimal.TEN);
        productDTO.setDescription("PRODUCT_DTO FOR TEST");
        return productDTO;
    }

    private ProductEntity entity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName("VICTIM_TWO");
        productEntity.setId(10L);
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setCategory(ProductCategory.MEAT);
        productEntity.setDiscount(BigDecimal.TEN);
        productEntity.setDescription("PRODUCT_ENTITY FOR TEST");
        return productEntity;
    }

}