package com.javaguru.shoppinglist.repository;
import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {
    private final ProductEntity ENTITY = new ProductEntity();
    @Spy
    private ProductRepository victim;

    @Test
    public void shouldSaveFirstProductInRepositoryAndChangeIDToZERO() {
        when(victim.save(entityInput())).thenReturn(entityOutput());
        assertEquals(entityOutput(), victim.save(entityInput()));
    }

    @Test
    public void shouldReturnProductWhenProductFoundByID() {
        when(victim.findProductById(0L)).thenReturn(Optional.ofNullable(entityOutput()));
        assertEquals(Optional.ofNullable(entityOutput()), victim.findProductById(0L));
    }

    @Test
    public void shouldReturnNullWhenProductNotFoundByID() {
        when(victim.findProductById(10L)).thenReturn(null);
        assertNull(victim.findProductById(10L));
    }

    @Test
    public void shouldFindProductByName() {
        when(victim.findProductByName(TestProductData.NAME)).thenReturn(Optional.ofNullable(entityOutput()));
        assertEquals(Optional.ofNullable(entityOutput()), victim.findProductByName(TestProductData.NAME));
    }

    @Test
    public void shouldReturnNullWhenProductNotFoundByName() {
        when(victim.findProductByName(TestProductData.NAME)).thenReturn(null);
        assertNull(victim.findProductByName(TestProductData.NAME));
    }

    @Test
    public void shouldReturnProductWhenProductRemovedByID() {
        when(victim.deleteProduct(0L)).thenReturn(Optional.ofNullable(entityOutput()));
        assertEquals(Optional.ofNullable(entityOutput()), victim.deleteProduct(0L));
    }

    @Test
    public void shouldReturnNullWhenProductNotDeletedByID() {
        when(victim.deleteProduct(10L)).thenReturn(null);
        assertNull(victim.deleteProduct(10L));
    }

    @Test
    public void shouldReplaceProductWithChangedParameters() {
        when(victim.changeProductParameters(20L, entityOutput())).thenReturn(Optional.ofNullable(entityOutput()));
        assertEquals(Optional.ofNullable(entityOutput()), victim.changeProductParameters(20L, entityOutput()));
    }

    @Test
    public void shouldReturnNullWhenProductNotFoundByIdForChangingParameters() {
        when(victim.changeProductParameters(20L, entityOutput())).thenReturn(null);
        assertNull(victim.changeProductParameters(20L, entityOutput()));
    }

    private ProductEntity entityInput() {
        ENTITY.setName(TestProductData.NAME);
        ENTITY.setPrice(TestProductData.PRICE);
        ENTITY.setCategory(TestProductData.CATEGORY);
        ENTITY.setDiscount(BigDecimal.TEN);
        ENTITY.setDescription(TestProductData.DESCRIPTION);
        return ENTITY;
    }

    private ProductEntity entityOutput() {
        ENTITY.setName(TestProductData.NAME);
        ENTITY.setId(0L);
        ENTITY.setPrice(TestProductData.PRICE);
        ENTITY.setCategory(TestProductData.CATEGORY);
        ENTITY.setDiscount(BigDecimal.TEN);
        ENTITY.setDescription(TestProductData.DESCRIPTION);
        return ENTITY;
    }
}