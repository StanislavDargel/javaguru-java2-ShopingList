package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductValidationServiceTest {
    private ProductDTO productDTO = new ProductDTO();
    private ProductValidationService victim = new ProductValidationService();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenProductNull() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product must be not null");

        victim.validateProduct(null);
    }

    @Test
    public void shouldThrowExceptionWhenProductNameNull() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product name must be not empty");

        victim.validateProduct(new ProductDTO());
    }

    @Test
    public void shouldThrowExceptionWhenProductNameEmpty() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product name must be not empty");

        productDTO.setName("");
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThenProductNameLengthLessThan3() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product name length is incorrect (3 - 32 letters)");

        productDTO.setName("12");
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThenProductNameLengthMoreThan32() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product name length is incorrect (3 - 32 letters)");

        productDTO.setName("Test Test Test Test Test Test 321");
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionWhenProductPriceIsNull() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product price is Incorrect, price must be more than ZERO");

        productDTO.setName("Test");
        productDTO.setPrice(null);
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionWhenProductPriceLessThanZero() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product price is Incorrect, price must be more than ZERO");

        productDTO.setName("Test");
        productDTO.setPrice(new BigDecimal(-0.01));
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThanProductHaveProductCategoryNull() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product category must be not null");
        productDTO.setName("Test");
        productDTO.setPrice(BigDecimal.TEN);
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThanProductDiscountIs100Percent() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product discount is Incorrect, (Correct bounds (0 ~ 100.0))");
        productDTO.setName("Test");
        productDTO.setPrice(BigDecimal.ONE);
        productDTO.setCategory(ProductCategory.FRUIT);
        productDTO.setDiscount(new BigDecimal(100));
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThanProductDiscountMoreThanOne() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product discount is Incorrect, (Correct bounds (0 ~ 100.0))");
        productDTO.setName("TesT");
        productDTO.setPrice(BigDecimal.ONE);
        productDTO.setCategory(ProductCategory.FRUIT);
        productDTO.setDiscount(new BigDecimal(100.01));
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThanProductDiscountLessThanZero() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product discount is Incorrect, (Correct bounds (0 ~ 100.0))");
        productDTO.setName("TesT");
        productDTO.setPrice(BigDecimal.ONE);
        productDTO.setCategory(ProductCategory.FRUIT);
        productDTO.setDiscount(new BigDecimal(-0.01));
        victim.validateProduct(productDTO);
    }

    @Test
    public void shouldThrowExceptionThanProductDescriptionIsEmpty() {
        exception.expect(ProductValidationExceptions.class);
        exception.expectMessage("Product description must be not empty");
        productDTO.setName("TesT");
        productDTO.setPrice(BigDecimal.TEN);
        productDTO.setCategory(ProductCategory.FRUIT);
        productDTO.setDiscount(new BigDecimal(0.254));
        productDTO.setDescription("");
        victim.validateProduct(productDTO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductValidationServiceTest)) return false;
        ProductValidationServiceTest that = (ProductValidationServiceTest) o;
        return Objects.equals(productDTO, that.productDTO) &&
                Objects.equals(victim, that.victim) &&
                Objects.equals(exception, that.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productDTO, victim, exception);
    }

    @Override
    public String toString() {
        return "ProductValidationServiceTest{" +
                "productDTO=" + productDTO +
                ", victim=" + victim +
                ", exception=" + exception +
                '}';
    }
}