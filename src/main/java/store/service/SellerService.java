package store.service;

import java.util.Optional;
import store.model.Seller;

public interface SellerService extends GenericService<Seller> {
    Optional<Seller> findByLogin(String login);
}
