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
public class ProductCategoryValidationRuleTest {
    private ProductDTO dto = new ProductDTO();

    @Spy
    private ProductCategoryValidationRule victim;

    @Test
    public void shouldThrowExceptionWhenProductCategoryNull() {
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.CATEGORY_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenCategoryNotNull() {
        dto.setCategory(TestProductData.CATEGORY);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }
}