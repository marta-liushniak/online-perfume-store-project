package store.service;

import java.util.List;
import store.model.Fragrance;
import store.model.Seller;

public interface FragranceService extends GenericService<Fragrance> {
    void addSellerToFragrance(Seller seller, Fragrance fragrance);

    void removeSellerFromFragrance(Seller seller, Fragrance fragrance);

    List<Fragrance> getAllBySeller(Long sellerId);
}
