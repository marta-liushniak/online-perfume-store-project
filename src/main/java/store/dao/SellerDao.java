package store.dao;

import java.util.Optional;
import store.model.Seller;

public interface SellerDao extends GenericDao<Seller> {
    Optional<Seller> findByLogin(String login);
}
