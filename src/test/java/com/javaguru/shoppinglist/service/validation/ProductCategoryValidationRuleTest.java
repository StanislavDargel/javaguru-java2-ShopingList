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
public class ProductCategoryValidationRuleTest {
    private ProductDTO DTO = new ProductDTO();

    @Spy
    @InjectMocks
    private ProductCategoryValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenProductCategoryNull() {
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.CATEGORY_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenCategoryNotNull() {
        DTO.setCategory(TestProductData.CATEGORY);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }
}