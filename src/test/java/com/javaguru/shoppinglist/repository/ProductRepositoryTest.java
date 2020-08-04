package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductRepositoryTest {
    private ProductEntity entity = new ProductEntity();
    private ProductRepository victim = new ProductRepositoryImpl();

    @Test
    public void shouldSaveFirstProductInRepositoryAndChangeIDToZERO() {
        assertEquals(entityOutput(), victim.save(entityInput()));
    }

    @Test
    public void shouldReturnProductWhenProductFoundByID() {
        victim.save(entityInput());
        assertEquals(Optional.ofNullable(entityOutput()), victim.findProductById(0L));
    }

    @Test
    public void shouldReturnNullWhenProductNotFoundByID() {
        assertEquals(Optional.empty(), victim.findProductById(10L));
    }

    @Test
    public void shouldFindProductByName() {
        victim.save(entityInput());
        assertEquals(Optional.ofNullable(entityOutput()), victim.findProductByName(TestProductData.NAME));
    }

    @Test
    public void shouldReturnNullWhenProductNotFoundByName() {
        victim.save(entityInput());
        assertEquals(Optional.empty(), victim.findProductByName("TestProductData.NAME"));
    }

    @Test
    public void shouldRemoveProductByID() {
        victim.save(entityInput());
        victim.deleteProduct(0L);
        assertEquals(Optional.empty(), victim.findProductById(0L));
    }

    @Test
    public void shouldReplaceProductWithChangedParameters() {
        victim.save(entityInput());
        assertEquals(Optional.ofNullable(entityOutput()), victim.changeProductParameters(0L, entityOutput()));
    }

    @Test
    public void shouldReturnNullWhenProductNotFoundByIdForChangingParameters() {
        victim.save(entityInput());
        assertEquals(Optional.empty(), victim.changeProductParameters(20L, entityOutput()));
    }

    private ProductEntity entityInput() {
        entity.setName(TestProductData.NAME);
        entity.setPrice(TestProductData.PRICE);
        entity.setCategory(TestProductData.CATEGORY);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription(TestProductData.DESCRIPTION);
        return entity;
    }

    private ProductEntity entityOutput() {
        entity.setName(TestProductData.NAME);
        entity.setId(0L);
        entity.setPrice(TestProductData.PRICE);
        entity.setCategory(TestProductData.CATEGORY);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription(TestProductData.DESCRIPTION);
        return entity;
    }
}