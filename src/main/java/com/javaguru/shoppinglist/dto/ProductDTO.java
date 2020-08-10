package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.domain.ShoppingCartEntity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private BigDecimal discount;
    private String description;
    private BigDecimal actualPrice;
    private Set<ShoppingCartEntity> shoppingCarts;

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
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

    public Set<ShoppingCartEntity> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCartEntity> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                category == that.category &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(actualPrice, that.actualPrice) &&
                Objects.equals(shoppingCarts, that.shoppingCarts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, discount, description, actualPrice, shoppingCarts);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", actualPrice=" + actualPrice +
                ", shoppingCarts=" + shoppingCarts +
                '}';
    }
}
