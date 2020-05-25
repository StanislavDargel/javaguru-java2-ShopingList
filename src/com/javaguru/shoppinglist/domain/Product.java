package com.javaguru.shoppinglist.domain;

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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", actualPrice=" + actualPrice +
                '}';
    }
}