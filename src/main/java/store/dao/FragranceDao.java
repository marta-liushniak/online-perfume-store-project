package store.dao;

import java.util.List;
import store.model.Fragrance;

public interface FragranceDao extends GenericDao<Fragrance> {
    List<Fragrance> getAllBySeller(Long sellerId);
}
