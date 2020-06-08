package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.constantparameters.TestProductData;
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

import static javax.print.attribute.standard.MediaSizeName.D;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueValidationRuleTest {
    private final ProductDTO DTO = new ProductDTO();
    private final ProductEntity ENTITY = new ProductEntity();

    @Mock
    private ProductRepository repository;

    @Spy
    @InjectMocks
    private ProductUniqueValidationRule victim;

    @Test
    public void shouldThrowExceptionProductNameMustBeUnique() {
        ENTITY.setName(TestProductData.NAME);
        when(repository.findProductByName(TestProductData.NAME)).thenReturn(Optional.of(ENTITY));
        DTO.setName(TestProductData.NAME);
        assertThatThrownBy(() -> victim.validate(DTO))
                .isInstanceOf(ProductValidationExceptions.class)
                .hasMessage(ValidationExceptionMessages.UNIQUE_NAME_VALIDATION_MESSAGE);
        verify(victim).productNotNull(DTO);
    }

    @Test
    public void shouldDoNotThrowExceptionWhenNameIsUnique() {
        DTO.setName(TestProductData.NAME);
        victim.validate(DTO);
        verify(victim).productNotNull(DTO);
    }
}