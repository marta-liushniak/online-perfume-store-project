package store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.dao.SellerDao;
import store.exception.DataProcessingException;
import store.lib.Dao;
import store.model.Seller;
import store.util.ConnectionUtil;

@Dao
public class SellerDaoImpl implements SellerDao {
    @Override
    public Seller create(Seller seller) {
        String query = "INSERT INTO sellers (name, last_name, login, password) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, seller.getName());
            statement.setString(2, seller.getLastName());
            statement.setString(3, seller.getLogin());
            statement.setString(4, seller.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                seller.setId(resultSet.getObject(1, Long.class));
            }
            return seller;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create a seller: "
                    + seller + ". ", e);
        }
    }

    @Override
    public Optional<Seller> get(Long id) {
        String query = "SELECT * FROM sellers WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Seller seller = null;
            if (resultSet.next()) {
                seller = parseSellerFromResultSet(resultSet);
            }
            return Optional.ofNullable(seller);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a seller by id " + id + ". ", e);
        }
    }

    @Override
    public List<Seller> getAll() {
        String query = "SELECT * FROM sellers WHERE is_deleted = FALSE";
        List<Seller> sellers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sellers.add(parseSellerFromResultSet(resultSet));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a list of sellers. ",
                    e);
        }
    }

    @Override
    public Seller update(Seller seller) {
        String query = "UPDATE sellers "
                + "SET name = ?, last_name = ?,"
                + "login = ?, password = ?"
                + "WHERE id = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setString(1, seller.getName());
            statement.setString(2, seller.getLastName());
            statement.setString(3, seller.getLogin());
            statement.setString(4, seller.getPassword());
            statement.setLong(5, seller.getId());
            statement.executeUpdate();
            return seller;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update a seller: "
                    + seller + ". ", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE sellers SET is_deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete a seller with id " + id + ". ", e);
        }
    }

    @Override
    public Optional<Seller> findByLogin(String login) {
        String query = "SELECT * FROM sellers WHERE login = ? AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            Seller seller = null;
            if (resultSet.next()) {
                seller = parseSellerFromResultSet(resultSet);
            }
            return Optional.ofNullable(seller);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a seller by login " + login + ". ", e);
        }
    }

    private Seller parseSellerFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String lastName = resultSet.getString("last_name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        Seller seller = new Seller();
        seller.setId(id);
        seller.setName(name);
        seller.setLastName(lastName);
        seller.setLogin(login);
        seller.setPassword(password);
        return seller;
    }
}
