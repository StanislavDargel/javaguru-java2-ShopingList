package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ProductEntity;
import com.javaguru.shoppinglist.mapper.ProductEntityRowMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("mysql")
public class MySQLProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public MySQLProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        String query = "INSERT INTO products (name, product_category, price, discount, description) " +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, productEntity.getName());
            preparedStatement.setString(2, String.valueOf(productEntity.getCategory()));
            preparedStatement.setBigDecimal(3, productEntity.getPrice());
            preparedStatement.setBigDecimal(4, productEntity.getDiscount());
            preparedStatement.setString(5, productEntity.getDescription());
            return preparedStatement;
        }, keyHolder);

        ProductEntity entity = new ProductEntity();
        entity.setId(keyHolder.getKey().longValue());
        entity.setName(productEntity.getName());
        entity.setCategory(productEntity.getCategory());
        entity.setPrice(productEntity.getPrice());
        entity.setDiscount(productEntity.getDiscount());
        entity.setDescription(productEntity.getDescription());
        return entity;
    }

    @Override
    public Optional<ProductEntity> findProductById(Long id) {
        String query = "SELECT * FROM products WHERE id = ?";
        ProductEntity entity = jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductEntityRowMapper());
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<ProductEntity> findProductByName(String name) {
        String query = "SELECT * FROM products WHERE name = ?";
        List<ProductEntity> products = jdbcTemplate.query(query, new Object[]{name}, new ProductEntityRowMapper());
        if (!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public void deleteProduct(Long id) {
        String query = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return preparedStatement;
        });
    }

    @Override
    public Optional<ProductEntity> changeProductParameters(Long id, ProductEntity productEntity) {
        String query = "UPDATE products SET name = ?, product_category = ?," +
                " price = ?, discount = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productEntity.getName());
            preparedStatement.setString(2, String.valueOf(productEntity.getCategory()));
            preparedStatement.setBigDecimal(3, productEntity.getPrice());
            preparedStatement.setBigDecimal(4, productEntity.getDiscount());
            preparedStatement.setString(5, productEntity.getDescription());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            return preparedStatement;
        });
        ProductEntity entity = new ProductEntity();
        entity.setId(id);
        entity.setName(productEntity.getName());
        entity.setCategory(productEntity.getCategory());
        entity.setPrice(productEntity.getPrice());
        entity.setDiscount(productEntity.getDiscount());
        entity.setDescription(productEntity.getDescription());
        return Optional.ofNullable(entity);
    }
}
