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
public class ProductDiscountValidationRuleTest {
    private ProductDTO dto = new ProductDTO();

    @Spy
    private ProductDiscountValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenDiscountLessThanZERO() {
        dto.setDiscount(TestProductData.NEGATIVE_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenDiscountEquals100() {
        dto.setDiscount(TestProductData.DISCOUNT_HUNDRED);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenDiscountMoreThan100() {
        dto.setDiscount(TestProductData.OUT_OF_BOUND_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionThanDiscountZERO() {
        dto.setDiscount(BigDecimal.ZERO);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionThanDiscountInRightBound() {
        dto.setDiscount(TestProductData.DISCOUNT_IN_RIGHT_BOUND);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }
}