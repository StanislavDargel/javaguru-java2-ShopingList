package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BeanMapperTest {
    private final ProductDTO DTO = new ProductDTO();
    private final ProductEntity ENTITY = new ProductEntity();
    @Spy
    private BeanMapper victim;

    @Test
    public void shouldConvertProductEntityIntoProductDTO() {
        ENTITY.setName(TestProductData.NAME);
        ENTITY.setId(10L);
        ENTITY.setPrice(TestProductData.PRICE);
        ENTITY.setCategory(TestProductData.CATEGORY);
        ENTITY.setDiscount(BigDecimal.TEN);
        ENTITY.setDescription(TestProductData.DESCRIPTION);
        ProductDTO actual = victim.toProductDTO(ENTITY);

        assertEquals(dto(), actual);
    }

    @Test
    public void shouldConvertProductDTOIntoProductEntity() {
        DTO.setName(TestProductData.NAME);
        DTO.setId(10L);
        DTO.setPrice(TestProductData.PRICE);
        DTO.setCategory(TestProductData.CATEGORY);
        DTO.setDiscount(BigDecimal.TEN);
        DTO.setDescription(TestProductData.DESCRIPTION);
        ProductEntity actual = victim.toProductEntity(DTO);

        assertEquals(entity(), actual);
    }

    private ProductDTO dto() {
        DTO.setName(TestProductData.NAME);
        DTO.setId(10L);
        DTO.setPrice(TestProductData.PRICE);
        DTO.setCategory(TestProductData.CATEGORY);
        DTO.setDiscount(BigDecimal.TEN);
        DTO.setDescription(TestProductData.DESCRIPTION);
        return DTO;
    }

    private ProductEntity entity() {
        ENTITY.setName(TestProductData.NAME);
        ENTITY.setId(10L);
        ENTITY.setPrice(TestProductData.PRICE);
        ENTITY.setCategory(TestProductData.CATEGORY);
        ENTITY.setDiscount(BigDecimal.TEN);
        ENTITY.setDescription(TestProductData.DESCRIPTION);
        return ENTITY;
    }

}