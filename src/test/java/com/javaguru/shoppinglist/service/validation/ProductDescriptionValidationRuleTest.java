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
public class ProductDescriptionValidationRuleTest {
    private ProductDTO dto = new ProductDTO();

    @Spy
    private ProductDescriptionValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenDescriptionIsEmpty() {
        dto.setDescription(TestProductData.EMPTY_DESCRIPTION);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DESCRIPTION_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenDescriptionNotEmpty() {
        dto.setDescription(TestProductData.DESCRIPTION);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }
}