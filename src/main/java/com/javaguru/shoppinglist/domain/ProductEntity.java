package com.javaguru.shoppinglist.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private ProductCategory category;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "sc_id", insertable = false, updatable = false)
    private ShoppingCartEntity shoppingCart;

    public ProductEntity() {
    }

    private ProductEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
        this.discount = builder.discount;
        this.description = builder.description;
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
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity entity = (ProductEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(price, entity.price) &&
                category == entity.category &&
                Objects.equals(discount, entity.discount) &&
                Objects.equals(description, entity.description) &&
                Objects.equals(shoppingCart, entity.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, discount, description, shoppingCart);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private BigDecimal price;
        private ProductCategory category;
        private BigDecimal discount;
        private String description;

        public Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setCategory(ProductCategory category) {
            this.category = category;
            return this;
        }

        public Builder setDiscount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder of(ProductEntity productEntity) {
            this.id = productEntity.id;
            this.name = productEntity.name;
            this.price = productEntity.price;
            this.category = productEntity.category;
            this.discount = productEntity.discount;
            this.description = productEntity.description;
            return this;
        }

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }
}