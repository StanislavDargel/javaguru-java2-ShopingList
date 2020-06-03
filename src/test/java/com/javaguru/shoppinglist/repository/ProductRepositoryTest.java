package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.domain.ProductEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.Assert.*;

public class ProductRepositoryTest {
    private final ProductRepository victim = new ProductRepositoryImpl();

    @Test
    public void shouldSaveFirstProductInRepositoryAndChangeIDToZERO() {
        ProductEntity actual = victim.save(testProductOne());

        assertNotNull(actual);
        assertEquals(testResultProductOne(), actual);
    }

    @Test
    public void shouldSaveAnotherProductAndChangeIdSequence() {
        victim.save(testProductOne());
        ProductEntity actual = victim.save(testProductTwo());

        assertNotNull(actual);
        assertEquals(testResultProductTwo(), actual);
    }

    @Test
    public void shouldReplaceProductIfProductNamesAreSame() {
        victim.save(testProductOne());
        victim.save(testProductOne());
        victim.save(testProductOne());
        ProductEntity actual = victim.save(testProductOne());
        assertNotNull(actual);
        assertEquals(testResultProductOne(), actual);
    }

    @Test
    public void shouldReturnProductThanProductFoundByID() {
        victim.save(testProductOne());
        victim.save(testProductTwo());
        ProductEntity actual = victim.findProductById(1L);

        assertNotNull(actual);
        assertEquals(testResultProductTwo(), actual);
    }

    @Test
    public void shouldReturnNullThanProductNotFoundByID() {
        victim.save(testProductOne());
        victim.save(testProductTwo());
        ProductEntity actual = victim.findProductById(2L);

        assertNull(actual);
    }

    @Test
    public void shouldDeleteProductByID() {
        victim.save(testProductOne());
        victim.save(testProductTwo());
        ProductEntity actual = victim.deleteProduct(0L);
        assertNull(victim.findProductById(0L));
        assertNotNull(actual);
        assertEquals(removableProduct(), actual);
    }

    @Test
    public void shouldReplaceProductWithChangedParameters() {
        victim.save(testProductOne());
        ProductEntity actual = victim.changeProductParameters(0L, testResultProductThree());
        assertNotNull(actual);
        assertEquals(testResultProductThree(), actual);
    }

    @Test
    public void shouldDoNothingIfProductNotFoundByIdForChangingParameters() {
        victim.save(testProductTwo());
        ProductEntity actual = victim.changeProductParameters(1L, testResultProductOne());
        assertNull(actual);
    }

    private ProductEntity removableProduct() {
        ProductEntity entity = new ProductEntity();
        entity.setName("TEST_ONE");
        entity.setId(0L);
        entity.setPrice(BigDecimal.ONE);
        entity.setCategory(ProductCategory.MEAT);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription("PRODUCT_ENTITY FOR TEST");
        return entity;
    }

    private ProductEntity testResultProductOne() {
        ProductEntity entity = new ProductEntity();
        entity.setName("TEST_ONE");
        entity.setId(0L);
        entity.setPrice(BigDecimal.ONE);
        entity.setCategory(ProductCategory.MEAT);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription("PRODUCT_ENTITY FOR TEST");
        return entity;
    }

    private ProductEntity testResultProductTwo() {
        ProductEntity entity = new ProductEntity();
        entity.setName("TEST_TWO");
        entity.setId(1L);
        entity.setPrice(BigDecimal.TEN);
        entity.setCategory(ProductCategory.CANNED_FOOD);
        entity.setDiscount(BigDecimal.ONE);
        entity.setDescription("PRODUCT_ENTITY FOR TEST");
        return entity;
    }

    private ProductEntity testProductOne() {
        ProductEntity entity = new ProductEntity();
        entity.setName("TEST_ONE");
        entity.setPrice(BigDecimal.ONE);
        entity.setCategory(ProductCategory.MEAT);
        entity.setDiscount(BigDecimal.TEN);
        entity.setDescription("PRODUCT_ENTITY FOR TEST");
        return entity;
    }

    private ProductEntity testProductTwo() {
        ProductEntity entity = new ProductEntity();
        entity.setName("TEST_TWO");
        entity.setPrice(BigDecimal.TEN);
        entity.setCategory(ProductCategory.CANNED_FOOD);
        entity.setDiscount(BigDecimal.ONE);
        entity.setDescription("PRODUCT_ENTITY FOR TEST");
        return entity;
    }

    private ProductEntity testResultProductThree() {
        ProductEntity entity = new ProductEntity();
        entity.setName("TEST_THREE");
        entity.setId(0L);
        entity.setPrice(BigDecimal.TEN);
        entity.setCategory(ProductCategory.MEAT);
        entity.setDiscount(BigDecimal.ZERO);
        entity.setDescription("THIRD_PRODUCT_ENTITY FOR TEST");
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRepositoryTest)) return false;
        ProductRepositoryTest that = (ProductRepositoryTest) o;
        return Objects.equals(victim, that.victim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(victim);
    }
}