package com.javaguru.shoppinglist.domain;

public enum ProductCategory {
    FRUIT("FRUIT"),
    DRINK("DRINK"),
    MEAT("MEAT"),
    VEGETABLE("VEGETABLE"),
    CANDY("CANDY"),
    DAIRY_PRODUCTS("DAIRY PRODUCTS"),
    BAKERY("BAKERY"),
    SEA_FOODS("SEA FOODS"),
    CANNED_FOOD("CANNED FOOD"),
    GROAT("GROAT");

    private String categoryName;

    ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public static void printProductCategory() {
        for (ProductCategory category : ProductCategory.values()) {
            System.out.println(category.ordinal() + ". " + category);
        }
    }

    @Override
    public String toString() {
        return categoryName;
    }
}