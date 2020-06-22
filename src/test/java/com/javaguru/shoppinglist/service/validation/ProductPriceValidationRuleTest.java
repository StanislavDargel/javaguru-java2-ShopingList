package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceValidationRuleTest {
    private ProductDTO dto = new ProductDTO();

    @Spy
    private ProductPriceValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenPriceNull() {
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenPriceLessThanZero() {
        dto.setPrice(TestProductData.NEGATIVE_PRICE);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenPriceEqualsZERO() {
        dto.setPrice(BigDecimal.ZERO);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionThanPriceMoreThanZERO() {
        dto.setPrice(TestProductData.PRICE);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }
}