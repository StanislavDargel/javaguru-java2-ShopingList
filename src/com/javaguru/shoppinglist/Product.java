package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private BigDecimal discount;
    private String description;
    private BigDecimal actualPrice;

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                category == product.category &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(description, product.description) &&
                Objects.equals(actualPrice, product.actualPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, discount, description, actualPrice);
    }

    @Override
    public String toString() {
        if (description != null && !description.isEmpty() && discount != null) {
            return "\nName: " + name +
                    "\nID: " + id +
                    "\nRegular Price: " + price +
                    "\nCategory: " + category +
                    "\nDiscount: " + discount.movePointRight(2) + "%" +
                    "\nActual Price: " + actualPrice +
                    "\nDescription: " + description;
        } else if (discount != null) {
            return "\nName: " + name +
                    "\nID: " + id +
                    "\nRegular Price: " + price +
                    "\nCategory: " + category +
                    "\nDiscount: " + discount.movePointRight(2) + "%" +
                    "\nActual Price: " + actualPrice;
        } else if (description != null && !description.isEmpty()) {
            return "\nName: " + name +
                    "\nID: " + id +
                    "\nActual Price: " + price +
                    "\nCategory: " + category +
                    "\nDescription: " + description;
        }
        return "\nName: " + name +
                "\nID: " + id +
                "\nActual Price: " + price +
                "\nCategory: " + category;
    }
}