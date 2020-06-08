package com.javaguru.shoppinglist.constantparameters;

import com.javaguru.shoppinglist.domain.ProductCategory;

import java.math.BigDecimal;

public class TestProductData {
    public static final Long ID_ZERO = 0L;
    public static final Long ID_ONE = 1L;
    public static final String EMPTY_NAME = "";
    public static final String NAME = "PRODUCT_NAME";
    public static final String TWO_LETTER_NAME = "AA";
    public static final String THREE_LETTER_NAME = "ABC";
    public static final String NAME_32_LETTERS = "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDD";
    public static final String NAME_33_LETTERS = "AAAAAAAAAABBBBBBBBBBCCCCCCCCCCDDD";
    public static final ProductCategory CATEGORY = ProductCategory.MEAT;
    public static final BigDecimal PRICE = new BigDecimal("20.00");
    public static final BigDecimal NEGATIVE_PRICE = new BigDecimal("-0.01");
    public static final BigDecimal DISCOUNT = BigDecimal.TEN;
    public static final BigDecimal DISCOUNT_ZERO = BigDecimal.ZERO;
    public static final BigDecimal DISCOUNT_IN_RIGHT_BOUND = new BigDecimal("99.9");
    public static final BigDecimal NEGATIVE_DISCOUNT = new BigDecimal("-0.1");
    public static final BigDecimal DISCOUNT_HUNDRED = new BigDecimal("100.0");
    public static final BigDecimal OUT_OF_BOUND_DISCOUNT = new BigDecimal("100.1");
    public static final String EMPTY_DESCRIPTION = "";
    public static final String DESCRIPTION = "PRODUCT_DESCRIPTION";
    public static final BigDecimal ACTUAL_PRICE = new BigDecimal("18.00");

    private TestProductData() {
    }
}
