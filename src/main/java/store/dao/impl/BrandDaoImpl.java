package store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.dao.BrandDao;
import store.exception.DataProcessingException;
import store.lib.Dao;
import store.model.Brand;
import store.util.ConnectionUtil;

@Dao
public class BrandDaoImpl implements BrandDao {
    @Override
    public Brand create(Brand brand) {
        String query = "INSERT INTO brands (brand_name, country) VALUES (?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement
                     = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setUpdate(statement, brand).executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                brand.setId(resultSet.getObject(1, Long.class));
            }
            return brand;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create a brand: " + brand + ". ", e);
        }
    }

    @Override
    public Optional<Brand> get(Long id) {
        String query = "SELECT * FROM brands WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Brand brand = null;
            if (resultSet.next()) {
                brand = parseBrandFromResultSet(resultSet);
            }
            return Optional.ofNullable(brand);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a brand by id " + id + ". ", e);
        }
    }

    @Override
    public List<Brand> getAll() {
        String query = "SELECT * FROM brands WHERE is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            List<Brand> brands = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                brands.add(parseBrandFromResultSet(resultSet));
            }
            return brands;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a list of brands. ", e);
        }
    }

    @Override
    public Brand update(Brand brand) {
        String query = "UPDATE brands SET brand_name = ?, country = ?"
                + " WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement
                     = setUpdate(connection.prepareStatement(query), brand)) {
            statement.setLong(3, brand.getId());
            statement.executeUpdate();
            return brand;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update a brand: "
                    + brand + ". ", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE brands SET is_deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete a brand with id " + id + ". ", e);
        }
    }

    private PreparedStatement setUpdate(PreparedStatement statement,
                                        Brand brand) throws SQLException {
        statement.setString(1, brand.getBrandName());
        statement.setString(2, brand.getCountry());
        return statement;
    }

    private Brand parseBrandFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String brandName = resultSet.getString("brand_name");
        String country = resultSet.getString("country");
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName(brandName);
        brand.setCountry(country);
        return brand;
    }
}
