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
    private final ProductDTO DTO = new ProductDTO();

    @Spy
    private ProductNameValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenNameNull() {
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.EMPTY_NAME_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        DTO.setName(TestProductData.EMPTY_NAME);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.EMPTY_NAME_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenNameLengthIsLessThan3Letters() {
        DTO.setName(TestProductData.TWO_LETTER_NAME);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldThrowExceptionWhenNameLengthIsMoreThan32Letters() {
        DTO.setName(TestProductData.NAME_33_LETTERS);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.INCORRECT_NAME_LENGTH_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenNameLengthIn3Letters() {
        DTO.setName(TestProductData.THREE_LETTER_NAME);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenNameLengthIn32Letters() {
        DTO.setName(TestProductData.NAME_32_LETTERS);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);

    }
}