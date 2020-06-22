package com.javaguru.shoppinglist.productutils;

import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;

import java.math.BigDecimal;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class ProductInfoTest {
    private ProductInfo victim = new ProductInfo();

    @Test
    public void shouldPrintProductInfoWithNameIdCategoryAndPriceParameters() {
        String expected = format("\nName: %s\nID: %d\nPrice: %.2f\nCategory: %s%n",
                TestProductData.NAME, 0L, TestProductData.PRICE, TestProductData.CATEGORY);
        assertEquals(expected, victim.print(dtoStandard(0L)));
    }

    @Test
    public void shouldPrintProductInfoWithNameIdCategoryDiscountAndPriceParameters() {
        String expected = format("\nName: %s\nID: %d\nPrice: %.2f\nCategory: %s%nDiscount: %.1f %% \nActual Price: %.2f%n",
                TestProductData.NAME, 0L, TestProductData.PRICE, TestProductData.CATEGORY, BigDecimal.TEN, TestProductData.ACTUAL_PRICE);
        assertEquals(expected, victim.print(dtoDiscount(0L)));
    }

    @Test
    public void shouldPrintProductInfoWithNameIdCategoryDescriptionAndPriceParameters() {
        String expected = format("\nName: %s\nID: %d\nPrice: %.2f\nCategory: %s%nDescription: %s%n",
                TestProductData.NAME, 0L, TestProductData.PRICE, TestProductData.CATEGORY, TestProductData.DESCRIPTION);
        assertEquals(expected, victim.print(dtoDescription(0L)));
    }

    @Test
    public void shouldPrintProductInfoWithAllParameters() {
        String expected = format("\nName: %s\nID: %d\nPrice: %.2f\nCategory: %s%nDescription: %s%nDiscount: %.1f %% \nActual Price: %.2f%n",
                TestProductData.NAME, 0L, TestProductData.PRICE, TestProductData.CATEGORY, TestProductData.DESCRIPTION, BigDecimal.TEN, TestProductData.ACTUAL_PRICE);
        assertEquals(expected, victim.print(dtoAllParameters(0L)));
    }

    private ProductDTO dtoStandard(Long id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(TestProductData.PRICE);
        return dto;
    }

    private ProductDTO dtoDiscount(Long id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(TestProductData.PRICE);
        dto.setDiscount(BigDecimal.TEN);
        dto.setActualPrice(TestProductData.ACTUAL_PRICE);
        return dto;
    }

    private ProductDTO dtoDescription(Long id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(TestProductData.PRICE);
        dto.setDescription(TestProductData.DESCRIPTION);
        return dto;
    }

    private ProductDTO dtoAllParameters(Long id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        dto.setName(TestProductData.NAME);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setPrice(TestProductData.PRICE);
        dto.setDiscount(BigDecimal.TEN);
        dto.setActualPrice(TestProductData.ACTUAL_PRICE);
        dto.setDescription(TestProductData.DESCRIPTION);
        return dto;
    }
}