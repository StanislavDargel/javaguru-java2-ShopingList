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
    private final ProductDTO DTO = new ProductDTO();

    @Spy
    private ProductDescriptionValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenDescriptionIsEmpty() {
        DTO.setDescription(TestProductData.EMPTY_DESCRIPTION);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.DESCRIPTION_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenDescriptionNotEmpty() {
        DTO.setDescription(TestProductData.DESCRIPTION);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }
}