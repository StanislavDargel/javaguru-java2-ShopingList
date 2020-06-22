package com.javaguru.shoppinglist.productutils;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductDataNormalizerTest {
    private ProductDataNormalizer victim = new ProductDataNormalizer();

    @Test
    public void shouldFormatProductsNameAndPriceLessThan20() {
        ProductDTO expected = new ProductDTO();
        expected.setId(0L);
        expected.setName("Product_name");
        expected.setCategory(TestProductData.CATEGORY);
        expected.setPrice(new BigDecimal("10.00"));
        expected.setDiscount(BigDecimal.ZERO);

        assertEquals(expected, victim.normalize(dtoNameAndPrice()));
    }

    @Test
    public void shouldFormatProductsNamePriceMoreThan20DiscountAndCalculateActualPrice() {
        ProductDTO expected = new ProductDTO();
        expected.setId(0L);
        expected.setName("Product_name");
        expected.setCategory(TestProductData.CATEGORY);
        expected.setPrice(new BigDecimal("20.00"));
        expected.setDiscount(new BigDecimal("10.0"));
        expected.setActualPrice(TestProductData.ACTUAL_PRICE);

        assertEquals(expected, victim.normalize(dtoNamePriceAndDiscount()));
    }

    @Test
    public void shouldFormatProductsNamePriceWithNamePriceLessThan20AndDescription() {
        ProductDTO expected = new ProductDTO();
        expected.setId(0L);
        expected.setName("Product_name");
        expected.setCategory(TestProductData.CATEGORY);
        expected.setPrice(new BigDecimal("10.00"));
        expected.setDiscount(BigDecimal.ZERO);
        expected.setDescription(TestProductData.DESCRIPTION);

        assertEquals(expected, victim.normalize(dtoNamePriceAndDescription()));
    }

    @Test
    public void shouldFormatProductWithAllParametersAndPriceMoreThan20() {
        ProductDTO expected = new ProductDTO();
        expected.setId(0L);
        expected.setName("Product_name");
        expected.setCategory(TestProductData.CATEGORY);
        expected.setPrice(new BigDecimal("20.00"));
        expected.setDiscount(new BigDecimal("10.0"));
        expected.setActualPrice(TestProductData.ACTUAL_PRICE);
        expected.setDescription(TestProductData.DESCRIPTION);

        assertEquals(expected, victim.normalize(dtoAllParameters()));
    }

    private ProductDTO dtoNameAndPrice() {
        ProductDTO dto = new ProductDTO();
        dto.setId(0L);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(new BigDecimal("10.000"));
        return dto;
    }

    private ProductDTO dtoNamePriceAndDiscount() {
        ProductDTO dto = new ProductDTO();
        dto.setId(0L);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(new BigDecimal("20.000000"));
        dto.setDiscount(BigDecimal.TEN);
        return dto;
    }

    private ProductDTO dtoNamePriceAndDescription() {
        ProductDTO dto = new ProductDTO();
        dto.setId(0L);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(new BigDecimal("10.000"));
        dto.setDescription(TestProductData.DESCRIPTION);
        return dto;
    }

    private ProductDTO dtoAllParameters() {
        ProductDTO dto = new ProductDTO();
        dto.setId(0L);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(new BigDecimal("20.000000"));
        dto.setDiscount(BigDecimal.TEN);
        dto.setDescription(TestProductData.DESCRIPTION);
        return dto;
    }
}