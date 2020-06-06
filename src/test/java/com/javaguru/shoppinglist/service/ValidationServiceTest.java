package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.BeanMapper;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductNotFoundException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductValidationService productValidationService;
    @Mock
    private BeanMapper beanMapper;
    @InjectMocks
    ValidationService victim;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test //Save product with Name, Price, Category: Got product with Name, ID, Price, Category
    public void shouldSaveProduct() {
        doNothing().when(productValidationService).validateProduct(createDTOOneIn());
        when(beanMapper.toProductEntity(createDTOOneIn())).thenReturn(createEntityOneIn());
        when(repository.save(createEntityOneIn())).thenReturn(createEntityOneOut());
        when(beanMapper.toProductDTO(createEntityOneOut())).thenReturn(createDTOOneOut());
        ProductDTO actual = victim.saveProduct(createDTOOneIn());
        assertNotNull(actual);
        assertEquals(createDTOOneOut(), actual);
    }

    @Test //Save product with Name, Price, Category, Discount : Got product with Name, ID, Price, Category, Discount
    public void shouldSaveProductWithDiscountParametersIfPriceMoreThan20() {
        doNothing().when(productValidationService).validateProduct(createDTOTwoIn());
        when(beanMapper.toProductEntity(createDTOTwoIn())).thenReturn(createEntityTwoIn());
        when(repository.save(createEntityTwoIn())).thenReturn(createEntityTwoOut());
        when(beanMapper.toProductDTO(createEntityTwoOut())).thenReturn(createDTOTwoOut());
        ProductDTO actual = victim.saveProduct(createDTOTwoIn());
        assertNotNull(actual);
        assertEquals(createDTOTwoOut(), actual);
    }

    @Test //Save product with Name, Price, Category, Discount : Got product with Name, ID, Price, Category, Discount
    public void shouldSaveProductWithDiscountParametersIfPriceLessThan20() {
        doNothing().when(productValidationService).validateProduct(createDTOTwoIn());
        when(beanMapper.toProductEntity(createDTOTwoIn())).thenReturn(createEntityTwoIn());
        when(repository.save(createEntityTwoIn())).thenReturn(createEntityTwoOut());
        when(beanMapper.toProductDTO(createEntityTwoOut())).thenReturn(createDTOFiveOut());
        ProductDTO actual = victim.saveProduct(createDTOTwoIn());
        assertNotNull(actual);
        assertEquals(createDTOFiveOut(), actual);
    }

    @Test
    //Save product with Name, Price, Category, Discount, Description : Got product with Name, ID, Price, Category, Discount, Description
    public void shouldSaveProductWithAllParametersAndPriceMoreThan20() {
        doNothing().when(productValidationService).validateProduct(createDTOThreeIn());
        when(beanMapper.toProductEntity(createDTOThreeIn())).thenReturn(createEntityThreeIn());
        when(repository.save(createEntityThreeIn())).thenReturn(createEntityThreeOut());
        when(beanMapper.toProductDTO(createEntityThreeOut())).thenReturn(createDTOThreeOut());
        ProductDTO actual = victim.saveProduct(createDTOThreeIn());
        assertNotNull(actual);
        assertEquals(createDTOThreeOut(), actual);
    }

    @Test
    public void shouldSaveProductWithAllParametersAndPriceLessThan20() {
        doNothing().when(productValidationService).validateProduct(createDTOThreeIn());
        when(beanMapper.toProductEntity(createDTOThreeIn())).thenReturn(createEntityThreeIn());
        when(repository.save(createEntityThreeIn())).thenReturn(createEntityThreeOut());
        when(beanMapper.toProductDTO(createEntityThreeOut())).thenReturn(createDTOSixOut());
        ProductDTO actual = victim.saveProduct(createDTOThreeIn());
        assertNotNull(actual);
        assertEquals(createDTOSixOut(), actual);
    }

    @Test
    //Save product with Name, Price, Category, Description : Got product with Name, ID, Price, Category, Description
    public void shouldSaveProductWithDescriptionParameters() {
        doNothing().when(productValidationService).validateProduct(createDTOFourIn());
        when(beanMapper.toProductEntity(createDTOFourIn())).thenReturn(createEntityFourIn());
        when(repository.save(createEntityFourIn())).thenReturn(createEntityFourOut());
        when(beanMapper.toProductDTO(createEntityFourOut())).thenReturn(createDTOFourOut());
        ProductDTO actual = victim.saveProduct(createDTOFourIn());
        assertNotNull(actual);
        assertEquals(createDTOFourOut(), actual);
    }

    @Test   // Product with standard parameters
    public void shouldFindProductOneById() {
        when(repository.findProductById(1L)).thenReturn(Optional.of(createEntityOneOut()));
        when(beanMapper.toProductDTO(createEntityOneOut())).thenReturn(createDTOOneOut());
        ProductDTO actual = victim.findById(1L);
        assertNotNull(actual);
        assertEquals(createDTOOneOut(), actual);
    }

    @Test   // Product with Name, Id, Price, Category, Discount
    public void shouldFindProductTwoById() {
        when(repository.findProductById(1L)).thenReturn(Optional.of(createEntityTwoOut()));
        when(beanMapper.toProductDTO(createEntityTwoOut())).thenReturn(createDTOTwoOut());
        ProductDTO actual = victim.findById(1L);
        assertNotNull(actual);
        assertEquals(createDTOTwoOut(), actual);
    }

    @Test   // Product with Name, ID, Price, Discount, Description
    public void shouldFindProductThreeById() {
        when(repository.findProductById(1L)).thenReturn(Optional.of(createEntityThreeOut()));
        when(beanMapper.toProductDTO(createEntityThreeOut())).thenReturn(createDTOThreeOut());
        ProductDTO actual = victim.findById(1L);
        assertNotNull(actual);
        assertEquals(createDTOThreeOut(), actual);
    }

    @Test   // Product with Name, ID, Price, Description
    public void shouldFindProductFourById() {
        when(repository.findProductById(1L)).thenReturn(Optional.of(createEntityFourOut()));
        when(beanMapper.toProductDTO(createEntityFourOut())).thenReturn(createDTOFourOut());
        ProductDTO actual = victim.findById(1L);
        assertNotNull(actual);
        assertEquals(createDTOFourOut(), actual);
    }

    @Test
    public void shouldReturnNullAndThrowExceptionThanProductNotFound() {
        Long id = 1L;
        exception.expect(ProductNotFoundException.class);
        exception.expectMessage("Product with ID " + id + " doesn't exist");

        when(repository.findProductById(1L)).thenReturn(null);
        ProductDTO actual = victim.findById(1L);
        assertNull(actual);
    }

    @Test
    public void shouldRemoveProduct() {
        Long id = 1L;
        exception.expect(ProductNotFoundException.class);
        exception.expectMessage("Product with ID " + id + " doesn't exist");
        when(repository.deleteProduct(1L)).thenReturn(Optional.of(createEntityOneOut()));
        when(repository.findProductById(1L)).thenReturn(null);
        when(beanMapper.toProductDTO(createEntityOneOut())).thenReturn(createDTOOneOut());
        assertEquals(createDTOOneOut(), victim.removeProduct(1L));
        assertNull(victim.findById(1L));

    }

    @Test
    public void shouldReplaceProductWithChangedParameters() {
        doNothing().when(productValidationService).validateProduct(createDTOThreeOut());
        when(repository.changeProductParameters(1L, createEntityThreeOut()))
                .thenReturn(Optional.of(createEntityThreeOut()));
        when(beanMapper.toProductEntity(createDTOThreeOut())).thenReturn(createEntityThreeOut());
        when(repository.findProductById(1L)).thenReturn(Optional.of(createEntityThreeOut()));
        when(beanMapper.toProductDTO(createEntityThreeOut())).thenReturn(createDTOThreeOut());
        ProductDTO actual = victim.changeParameters(1L, createDTOThreeOut());
        assertNotNull(actual);
        assertEquals(createDTOThreeOut(), actual);
    }

    @Test
    public void shouldDoNothingIfProductNotFoundByIdForChangingParameters() {
        Long id = 1L;
        exception.expect(ProductNotFoundException.class);
        exception.expectMessage("Product with ID " + id + " doesn't exist");
        when(repository.findProductById(1L)).thenReturn(null);
        ProductDTO actual = victim.changeParameters(1L, createDTOThreeOut());
        assertNull(actual);
    }


    private ProductDTO createDTOOneIn() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.DRINK);
        return product;
    }

    private ProductDTO createDTOOneOut() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setId(1L);
        product.setPrice(BigDecimal.ONE.setScale(2, RoundingMode.HALF_EVEN));
        product.setDiscount(BigDecimal.ZERO);
        product.setCategory(ProductCategory.DRINK);
        return product;
    }

    private ProductDTO createDTOTwoIn() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setPrice(new BigDecimal(21.00).setScale(2, RoundingMode.HALF_EVEN));
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(new BigDecimal(25.1).setScale(1, RoundingMode.FLOOR));
        return product;
    }

    private ProductDTO createDTOTwoOut() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setId(1L);
        product.setPrice(new BigDecimal(21.00).setScale(2, RoundingMode.HALF_EVEN));
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(new BigDecimal(25.1).setScale(1, RoundingMode.FLOOR));
        product.setActualPrice(product.getPrice().subtract(product.getPrice().movePointLeft(2).multiply(product.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
        return product;
    }

    private ProductDTO createDTOThreeIn() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setPrice(new BigDecimal(21.00).setScale(2, RoundingMode.HALF_EVEN));
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(new BigDecimal(25.1).setScale(1, RoundingMode.FLOOR));
        product.setDescription("Testers test");
        return product;
    }

    private ProductDTO createDTOThreeOut() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setId(1L);
        product.setPrice(new BigDecimal(21.00).setScale(2, RoundingMode.HALF_EVEN));
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(new BigDecimal(25.1).setScale(1, RoundingMode.FLOOR));
        product.setActualPrice(product.getPrice().subtract(product.getPrice().movePointLeft(2).multiply(product.getDiscount())).setScale(2, RoundingMode.HALF_EVEN));
        product.setDescription("Testers test");
        return product;
    }

    private ProductDTO createDTOFourIn() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.CANDY);
        product.setDescription("Testers test");
        return product;
    }

    private ProductDTO createDTOFourOut() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setId(1L);
        product.setPrice(BigDecimal.ONE.setScale(2, RoundingMode.HALF_EVEN));
        product.setDiscount(BigDecimal.ZERO);
        product.setCategory(ProductCategory.CANDY);
        product.setDescription("Testers test");
        return product;
    }

    private ProductDTO createDTOFiveOut() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setId(1L);
        product.setPrice(new BigDecimal(21.00).setScale(2, RoundingMode.HALF_EVEN));
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(BigDecimal.ZERO.setScale(1, RoundingMode.FLOOR));
        product.setActualPrice(product.getPrice());
        return product;
    }

    private ProductDTO createDTOSixOut() {
        ProductDTO product = new ProductDTO();
        product.setName("Test product");
        product.setId(1L);
        product.setPrice(new BigDecimal(21.00).setScale(2, RoundingMode.HALF_EVEN));
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(BigDecimal.ZERO.setScale(1, RoundingMode.FLOOR));
        product.setActualPrice(product.getPrice());
        product.setDescription("Testers test");
        return product;
    }

    private ProductEntity createEntityOneIn() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.DRINK);
        return product;
    }

    private ProductEntity createEntityOneOut() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setId(1L);
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.DRINK);
        return product;
    }

    private ProductEntity createEntityTwoIn() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(new BigDecimal(0.25));
        return product;
    }

    private ProductEntity createEntityTwoOut() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setId(1L);
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.DRINK);
        product.setDiscount(new BigDecimal(0.25));
        return product;
    }

    private ProductEntity createEntityThreeIn() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.FRUIT);
        product.setDiscount(new BigDecimal(0.25));
        product.setDescription("Testers test");
        return product;
    }

    private ProductEntity createEntityThreeOut() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setId(1L);
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.FRUIT);
        product.setDiscount(BigDecimal.ONE);
        product.setDescription("Testers test");
        return product;
    }

    private ProductEntity createEntityFourIn() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.CANDY);
        product.setDescription("Testers test");
        return product;
    }

    private ProductEntity createEntityFourOut() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setId(1L);
        product.setPrice(BigDecimal.ONE);
        product.setCategory(ProductCategory.CANDY);
        product.setDescription("Testers test");
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationServiceTest)) return false;
        ValidationServiceTest that = (ValidationServiceTest) o;
        return Objects.equals(repository, that.repository) &&
                Objects.equals(productValidationService, that.productValidationService) &&
                Objects.equals(beanMapper, that.beanMapper) &&
                Objects.equals(victim, that.victim) &&
                Objects.equals(exception, that.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository, productValidationService, beanMapper, victim, exception);
    }

    @Override
    public String toString() {
        return "ValidationServiceTest{" +
                "repository=" + repository +
                ", productValidationService=" + productValidationService +
                ", beanMapper=" + beanMapper +
                ", victim=" + victim +
                ", exception=" + exception +
                '}';
    }
}