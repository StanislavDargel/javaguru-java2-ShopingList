package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.constantparameters.TestProductData;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceValidationRuleTest {
    private final ProductDTO DTO = new ProductDTO();
    @Spy
    @InjectMocks
    private ProductPriceValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenPriceNull() {
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenPriceLessThanZero() {
        DTO.setPrice(TestProductData.NEGATIVE_PRICE);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenPriceEqualsZERO() {
        DTO.setPrice(BigDecimal.ZERO);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRICE_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionThanPriceMoreThanZERO() {
        DTO.setPrice(TestProductData.PRICE);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }
}