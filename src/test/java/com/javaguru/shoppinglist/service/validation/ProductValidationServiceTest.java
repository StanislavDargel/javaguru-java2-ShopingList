package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {
    @Mock
    private ProductCategoryValidationRule productCategoryValidationRule;
    @Mock
    private ProductDescriptionValidationRule productDescriptionValidationRule;
    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;
    @Mock
    private ProductNameValidationRule productNameValidationRule;
    @Mock
    private ProductPriceValidationRule productPriceValidationRule;
    @Mock
    private ProductUniqueValidationRule productUniqueValidationRule;

    @Captor
    private ArgumentCaptor<ProductDTO> captor;
    private ProductValidationService victim;
    private final ProductDTO DTO = dto();

    @Before
    public void setUo() {
        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(productCategoryValidationRule);
        rules.add(productDescriptionValidationRule);
        rules.add(productDiscountValidationRule);
        rules.add(productNameValidationRule);
        rules.add(productPriceValidationRule);
        rules.add(productUniqueValidationRule);

        victim = new ProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validateProduct(DTO);

        verify(productCategoryValidationRule).validate(captor.capture());
        verify(productDescriptionValidationRule).validate(captor.capture());
        verify(productDiscountValidationRule).validate(captor.capture());
        verify(productNameValidationRule).validate(captor.capture());
        verify(productPriceValidationRule).validate(captor.capture());
        verify(productUniqueValidationRule).validate(captor.capture());

        List<ProductDTO> resultList = captor.getAllValues();
        assertThat(resultList).containsOnly(DTO);
    }

    private ProductDTO dto() {
        ProductDTO dto = new ProductDTO();
        dto.setId(0L);
        dto.setCategory(TestProductData.CATEGORY);
        dto.setName(TestProductData.NAME);
        dto.setDescription(TestProductData.DESCRIPTION);
        dto.setPrice(TestProductData.PRICE);
        dto.setDiscount(BigDecimal.TEN);
        dto.setActualPrice(TestProductData.ACTUAL_PRICE);
        return dto;
    }

}
