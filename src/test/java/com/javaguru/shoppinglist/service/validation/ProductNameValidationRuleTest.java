package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {
    private ProductDTO dto = new ProductDTO();

    @Spy
    private ProductNameValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenNameNull() {
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.EMPTY_NAME_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        dto.setName(TestProductData.EMPTY_NAME);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.EMPTY_NAME_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenNameLengthIsLessThan3Letters() {
        dto.setName(TestProductData.TWO_LETTER_NAME);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldThrowExceptionWhenNameLengthIsMoreThan32Letters() {
        dto.setName(TestProductData.NAME_33_LETTERS);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenNameLengthIn3Letters() {
        dto.setName(TestProductData.THREE_LETTER_NAME);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenNameLengthIn32Letters() {
        dto.setName(TestProductData.NAME_32_LETTERS);
        victim.validate(dto);
        verify(victim).productNotNull(dto);

    }
}