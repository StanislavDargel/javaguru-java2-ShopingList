package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.testproductparameters.TestProductData;
import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotFoundException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.ValidationExceptionMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {
    private final ProductDTO DTO = new ProductDTO();
    private final ProductEntity ENTITY = new ProductEntity();

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductValidationService productValidationService;
    @Mock
    private BeanMapper beanMapper;
    @Spy
    @InjectMocks
    private ValidationService victim;

    @Test
    public void shouldSaveProductWithCategoryNamePrice() {
        when(repository.save(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoStandard(20L));
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        ProductDTO actual = victim.saveProduct(DTO);

        verify(productValidationService).validateProduct(any());
        assertEquals(dtoStandard(20L), actual);
    }

    @Test
    public void shouldSaveProductWithCategoryNameDiscountPriceEquals20() {
        when(repository.save(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoDiscount(120L));
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        ProductDTO actual = victim.saveProduct(DTO);

        verify(productValidationService).validateProduct(any());
        assertEquals(dtoDiscount(120L), actual);
    }

    @Test
    public void shouldSaveProductWithCategoryNameDiscountPriceLessThan20() {
        ProductDTO expected = dtoStandard(120L);
        expected.setPrice(BigDecimal.TEN);
        when(repository.save(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(expected);
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        ProductDTO actual = victim.saveProduct(DTO);

        verify(productValidationService).validateProduct(any());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveProductWithCategoryNamePriceDescription() {
        when(repository.save(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoDescription(200L));
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        ProductDTO actual = victim.saveProduct(DTO);

        verify(productValidationService).validateProduct(any());
        assertEquals(dtoDescription(200L), actual);
    }

    @Test
    public void shouldSaveProductWithAllParametersAndPriceMoreThan20() {
        when(repository.save(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoAllParameters(100L));
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        ProductDTO actual = victim.saveProduct(DTO);

        verify(productValidationService).validateProduct(any());
        assertEquals(dtoAllParameters(100L), actual);
    }

    @Test
    public void shouldSaveProductWithAllParametersAndPriceLessThan20() {
        ProductDTO expected = dtoDescription(120L);
        expected.setPrice(BigDecimal.TEN);
        when(repository.save(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(expected);
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        ProductDTO actual = victim.saveProduct(DTO);

        verify(productValidationService).validateProduct(any());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindProductWithStandardParametersById() {
        when(repository.findProductById(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoStandard(123L));
        ProductDTO actual = victim.findById(123L);
        assertEquals(dtoStandard(123L), actual);
    }

    @Test   // Product with Name, Id, Price, Category, Discount
    public void shouldFindProductWithStandardAndDiscountParametersById() {
        when(repository.findProductById(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoDiscount(123L));
        ProductDTO actual = victim.findById(123L);
        assertEquals(dtoDiscount(123L), actual);
    }

    @Test
    public void shouldFindProductWithStandardAndDescriptionParametersById() {
        when(repository.findProductById(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoDescription(123L));
        ProductDTO actual = victim.findById(123L);
        assertEquals(dtoDescription(123L), actual);
    }

    @Test   // Product with Name, ID, Price, Description
    public void shouldFindProductWithAllParametersById() {
        when(repository.findProductById(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoAllParameters(123L));
        ProductDTO actual = victim.findById(123L);
        assertEquals(dtoAllParameters(123L), actual);
    }

    @Test
    public void shouldThrowExceptionThanProductNotFound() {
        assertThatThrownBy(() -> victim.findById(any()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE);
    }

    @Test
    public void shouldRemoveStandardProduct() {
        when(repository.deleteProduct(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoStandard(123L));
        ProductDTO actual = victim.removeProduct(123L);
        assertEquals(dtoStandard(123L), actual);
    }

    @Test
    public void shouldRemoveStandardProductWithDiscount() {
        when(repository.deleteProduct(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoDiscount(123L));
        ProductDTO actual = victim.removeProduct(123L);
        assertEquals(dtoDiscount(123L), actual);
    }

    @Test
    public void shouldRemoveStandardProductWithDescription() {
        when(repository.deleteProduct(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoDescription(123L));
        ProductDTO actual = victim.removeProduct(123L);
        assertEquals(dtoDescription(123L), actual);
    }

    @Test
    public void shouldRemoveProductWithAllParameters() {
        when(repository.deleteProduct(123L)).thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoAllParameters(123L));
        ProductDTO actual = victim.removeProduct(123L);
        assertEquals(dtoAllParameters(123L), actual);
    }

    @Test
    public void shouldThrowExceptionThanProductForRemovingNotFounded() {
        assertThatThrownBy(() -> victim.removeProduct(any()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE);
    }

    @Test
    public void shouldReplaceProductWithChangedParameters() {
        when(repository.changeProductParameters(123L, ENTITY))
                .thenReturn(Optional.of(ENTITY));
        when(beanMapper.toProductEntity(any())).thenReturn(ENTITY);
        when(beanMapper.toProductDTO(ENTITY)).thenReturn(dtoAllParameters(123L));
        ProductDTO actual = victim.changeParameters(123L, dtoAllParameters(123L));
        assertEquals(dtoAllParameters(123L), actual);
    }

    @Test
    public void shouldThrowExceptionWhenProductNotFoundByIdForChangingParameters() {
        assertThatThrownBy(() -> victim.changeParameters(10L, dtoAllParameters(10L)))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage(ValidationExceptionMessages.PRODUCT_NOT_FOUND_MESSAGE);
    }


    private ProductDTO dtoStandard(Long id) {
        DTO.setId(id);
        DTO.setName(TestProductData.NAME);
        DTO.setCategory(TestProductData.CATEGORY);
        DTO.setPrice(TestProductData.PRICE);
        return DTO;
    }

    private ProductDTO dtoDiscount(Long id) {
        DTO.setId(id);
        DTO.setName(TestProductData.NAME);
        DTO.setCategory(TestProductData.CATEGORY);
        DTO.setPrice(TestProductData.PRICE);
        DTO.setDiscount(BigDecimal.TEN);
        DTO.setActualPrice(TestProductData.ACTUAL_PRICE);
        return DTO;
    }

    private ProductDTO dtoDescription(Long id) {
        DTO.setId(id);
        DTO.setName(TestProductData.NAME);
        DTO.setCategory(TestProductData.CATEGORY);
        DTO.setPrice(TestProductData.PRICE);
        DTO.setDescription(TestProductData.DESCRIPTION);
        return DTO;
    }

    private ProductDTO dtoAllParameters(Long id) {
        DTO.setId(id);
        DTO.setName(TestProductData.NAME);
        DTO.setCategory(TestProductData.CATEGORY);
        DTO.setPrice(TestProductData.PRICE);
        DTO.setDiscount(BigDecimal.TEN);
        DTO.setActualPrice(TestProductData.ACTUAL_PRICE);
        DTO.setDescription(TestProductData.DESCRIPTION);
        return DTO;
    }
}