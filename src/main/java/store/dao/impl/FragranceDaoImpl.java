package store.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import store.dao.FragranceDao;
import store.exception.DataProcessingException;
import store.lib.Dao;
import store.model.Fragrance;
import store.model.Seller;
import store.model.Brand;
import store.util.ConnectionUtil;

@Dao
public class FragranceDaoImpl implements FragranceDao {
    private static final int ZERO_PLACEHOLDER = 0;
    private static final int SHIFT = 2;

    @Override
    public Fragrance create(Fragrance fragrance) {
        String query = "INSERT INTO fragrances (fragrance_name, brand_id)"
                + "VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, fragrance.getFragranceName());
            statement.setLong(2, fragrance.getBrand().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                fragrance.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create a fragrance: " + fragrance + ". ", e);
        }
        insertAllSellers(fragrance);
        return fragrance;
    }

    @Override
    public Optional<Fragrance> get(Long id) {
        String query = "SELECT f.id AS id, "
                + "fragrance_name, "
                + "brand_id, "
                + "br.brand_name AS brand_name, "
                + "br.country AS brand_country "
                + "FROM fragrances f "
                + "JOIN brands br ON f.brand_id = br.id "
                + "WHERE f.id = ? AND f.is_deleted = FALSE";
        Fragrance fragrance = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                fragrance = parseFragranceFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get a fragrance by id: " + id + ". ", e);
        }
        if (fragrance != null) {
            fragrance.setSellers(getAllSellersByFragranceId(fragrance.getId()));
        }
        return Optional.ofNullable(fragrance);
    }

    @Override
    public List<Fragrance> getAll() {
        String query = "SELECT f.id AS id, "
                + "fragrance_name, "
                + "brand_id, "
                + "br.brand_name AS brand_name, "
                + "br.country AS brand_country "
                + "FROM fragrances f"
                + " JOIN brands br ON f.brand_id = br.id"
                + " WHERE f.is_deleted = FALSE";
        List<Fragrance> fragrances = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fragrances.add(parseFragranceFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all fragrances. ", e);
        }
        fragrances.forEach(fragrance -> fragrance.setSellers(getAllSellersByFragranceId(fragrance.getId())));
        return fragrances;
    }

    @Override
    public Fragrance update(Fragrance fragrance) {
        String query = "UPDATE fragrances SET fragrance_name = ?, brand_id = ? WHERE id = ?"
                + " AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setString(1, fragrance.getFragranceName());
            statement.setLong(2, fragrance.getBrand().getId());
            statement.setLong(3, fragrance.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update a fragrance: " + fragrance + ". ", e);
        }
        deleteAllSellersExceptList(fragrance);
        insertAllSellers(fragrance);
        return fragrance;
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE fragrances SET is_deleted = TRUE WHERE id = ?"
                + " AND is_deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete a fragrance by id " + id + ". ", e);
        }
    }

    @Override
    public List<Fragrance> getAllBySeller(Long sellerId) {
        String query = "SELECT f.id AS id, "
                + "fragrance_name, "
                + "brand_id, "
                + "br.brand_name AS brand_name, "
                + "br.country AS brand_country "
                + "FROM fragrances f"
                + " JOIN brands br ON f.brand_id = br.id"
                + " JOIN fragrances_sellers fs ON f.id = fs.fragrance_id"
                + " JOIN sellers s ON fs.seller_id = s.id"
                + " WHERE f.is_deleted = FALSE AND seller_id = ?"
                + " AND s.is_deleted = FALSE";
        List<Fragrance> fragrances = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setLong(1, sellerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                fragrances.add(parseFragranceFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all fragrances. ", e);
        }
        fragrances.forEach(fragrance -> fragrance.setSellers(getAllSellersByFragranceId(fragrance.getId())));
        return fragrances;
    }

    private void insertAllSellers(Fragrance fragrance) {
        Long fragranceId = fragrance.getId();
        List<Seller> sellers = fragrance.getSellers();
        if (sellers.size() == 0) {
            return;
        }
        String query = "INSERT INTO fragrances_sellers (fragrance_id, seller_id) VALUES "
                + sellers.stream().map(seller -> "(?, ?)").collect(Collectors.joining(", "))
                + " ON DUPLICATE KEY UPDATE fragrance_id = fragrance_id";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            for (int i = 0; i < sellers.size(); i++) {
                Seller seller = sellers.get(i);
                statement.setLong((i * SHIFT) + 1, fragranceId);
                statement.setLong((i * SHIFT) + 2, seller.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert sellers " + sellers + ". ", e);
        }
    }

    private Fragrance parseFragranceFromResultSet(ResultSet resultSet) throws SQLException {
        Long brandId = resultSet.getObject("brand_id", Long.class);
        String brandName = resultSet.getNString("brand_name");
        String brandCountry = resultSet.getNString("brand_country");
        Brand brand = new Brand();
        brand.setId(brandId);
        brand.setBrandName(brandName);
        brand.setCountry(brandCountry);
        Long fragranceId = resultSet.getObject("id", Long.class);
        String fragranceName = resultSet.getNString("fragrance_name");
        Fragrance fragrance = new Fragrance();
        fragrance.setId(fragranceId);
        fragrance.setFragranceName(fragranceName);
        fragrance.setBrand(brand);
        return fragrance;
    }

    private List<Seller> getAllSellersByFragranceId(Long fragranceId) {
        String query = "SELECT id, name, last_name, login, password "
                + "FROM fragrances_sellers fs "
                + "JOIN sellers s ON fs.seller_id = s.id "
                + "WHERE fragrance_id = ? AND is_deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setLong(1, fragranceId);
            ResultSet resultSet = statement.executeQuery();
            List<Seller> sellers = new ArrayList<>();
            while (resultSet.next()) {
                sellers.add(parseSellerFromResultSet(resultSet));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all sellers by fragrance id " + fragranceId + ". ", e);
        }
    }

    private Seller parseSellerFromResultSet(ResultSet resultSet) throws SQLException {
        Long sellerId = resultSet.getObject("id", Long.class);
        String name = resultSet.getNString("name");
        String lastName = resultSet.getNString("last_name");
        String login = resultSet.getNString("login");
        String password = resultSet.getNString("password");
        Seller seller = new Seller();
        seller.setId(sellerId);
        seller.setName(name);
        seller.setLastName(lastName);
        seller.setLogin(login);
        seller.setPassword(password);
        return seller;
    }

    private void deleteAllSellersExceptList(Fragrance fragrance) {
        Long fragranceId = fragrance.getId();
        List<Seller> exceptions = fragrance.getSellers();
        int size = exceptions.size();
        String query = "DELETE FROM fragrances_sellers WHERE fragrance_id = ? "
                + "AND NOT fragrances_sellers.seller_id IN ("
                + ZERO_PLACEHOLDER + ", ?".repeat(size)
                + ");";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(query)) {
            statement.setLong(1, fragranceId);
            for (int i = 0; i < size; i++) {
                Seller seller = exceptions.get(i);
                statement.setLong((i) + SHIFT, seller.getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete sellers: " + exceptions + ". ", e);
        }
    }
}
