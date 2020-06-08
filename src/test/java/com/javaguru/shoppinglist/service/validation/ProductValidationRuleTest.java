package com.javaguru.shoppinglist.service.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationRuleTest {
    @Spy
    private ProductValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenProductNull() {
        assertThatThrownBy(() -> victim.productNotNull(null))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.PRODUCT_VALIDATION_MESSAGE);
    }
}