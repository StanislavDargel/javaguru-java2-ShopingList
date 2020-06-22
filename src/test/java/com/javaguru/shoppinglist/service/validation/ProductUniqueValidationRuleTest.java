package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueValidationRuleTest {
    private ProductDTO dto = new ProductDTO();
    private ProductEntity entity = new ProductEntity();

    @Mock
    private ProductRepository repository;

    @Spy
    @InjectMocks
    private ProductUniqueValidationRule victim;

    @Test
    public void shouldThrowExceptionProductNameMustBeUnique() {
        entity.setName(TestProductData.NAME);
        when(repository.findProductByName(TestProductData.NAME)).thenReturn(Optional.of(entity));
        dto.setName(TestProductData.NAME);
        assertThatThrownBy(() -> victim.validate(dto))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.UNIQUE_NAME_VALIDATION_MESSAGE);
        verify(victim).productNotNull(dto);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenNameIsUnique() {
        dto.setName(TestProductData.NAME);
        victim.validate(dto);
        verify(victim).productNotNull(dto);
    }
}