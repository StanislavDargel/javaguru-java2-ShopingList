package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BeanMapperTest {
    private ProductDTO dto = new ProductDTO();
    private ProductEntity entity = new ProductEntity();
    private BeanMapper victim = new BeanMapper();

    @Test
    public void shouldConvertProductEntityIntoProductDTO() {
        entity.setName(TestProductData.NAME);
        entity.setId(10L);
        entity.setPrice(TestProductData.PRICE);
        entity.setCategory(TestProductData.CATEGORY);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription(TestProductData.DESCRIPTION);
        ProductDTO actual = victim.toProductDTO(entity);

        assertEquals(dto(), actual);
    }

    @Test
    public void shouldConvertProductDTOIntoProductEntity() {
        dto.setName(TestProductData.NAME);
        dto.setId(10L);
        dto.setPrice(TestProductData.PRICE);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setDiscount(BigDecimal.TEN);
        dto.setDescription(TestProductData.DESCRIPTION);
        ProductEntity actual = victim.toProductEntity(dto);

        assertEquals(entity(), actual);
    }

    private ProductDTO dto() {
        dto.setName(TestProductData.NAME);
        dto.setId(10L);
        dto.setPrice(TestProductData.PRICE);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setDiscount(BigDecimal.TEN);
        dto.setDescription(TestProductData.DESCRIPTION);
        return dto;
    }

    private ProductEntity entity() {
        entity.setName(TestProductData.NAME);
        entity.setId(10L);
        entity.setPrice(TestProductData.PRICE);
        entity.setCategory(TestProductData.CATEGORY);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription(TestProductData.DESCRIPTION);
        return entity;
    }

}