package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.ProductCategory;
import com.javaguru.shoppinglist.domain.ProductEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductEntityRowMapper implements RowMapper<ProductEntity> {

    @Override
    public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductEntity.Builder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setCategory(ProductCategory.valueOf(rs.getString("product_category")))
                .setPrice(rs.getBigDecimal("price"))
                .setDiscount(rs.getBigDecimal("discount"))
                .setDescription(rs.getString("description"))
                .build();
    }
}
