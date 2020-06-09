package com.javaguru.shoppinglist.repository;
import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {
    private final ProductEntity ENTITY = new ProductEntity();
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
    public void shouldReturnProductWhenProductRemovedByID() {
        victim.save(entityInput());
        assertEquals(Optional.ofNullable(entityOutput()), victim.deleteProduct(0L));
    }

    @Test
    public void shouldReturnNullWhenProductNotDeletedByID() {
        victim.save(entityInput());
        assertEquals(Optional.empty(), victim.deleteProduct(10L));
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