package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.constantparameters.TestProductData;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationRuleTest {
    private final ProductDTO DTO = new ProductDTO();

    @Spy
    @InjectMocks
    private ProductDiscountValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenDiscountLessThanZERO() {
        DTO.setDiscount(TestProductData.NEGATIVE_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenDiscountEquals100() {
        DTO.setDiscount(TestProductData.DISCOUNT_HUNDRED);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenDiscountMoreThan100() {
        DTO.setDiscount(TestProductData.OUT_OF_BOUND_DISCOUNT);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DISCOUNT_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionThanDiscountZERO() {
        DTO.setDiscount(TestProductData.DISCOUNT_ZERO);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionThanDiscountInRightBound() {
        DTO.setDiscount(TestProductData.DISCOUNT_IN_RIGHT_BOUND);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }
}