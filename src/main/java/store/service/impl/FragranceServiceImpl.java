package store.service.impl;

import java.util.List;
import store.dao.FragranceDao;
import store.lib.Inject;
import store.lib.Service;
import store.model.Fragrance;
import store.model.Seller;
import store.service.FragranceService;

@Service
public class FragranceServiceImpl implements FragranceService {
    @Inject
    private FragranceDao fragranceDao;

    @Override
    public void addSellerToFragrance(Seller seller, Fragrance fragrance) {
        fragrance.getSellers().add(seller);
        fragranceDao.update(fragrance);
    }

    @Override
    public void removeSellerFromFragrance(Seller seller, Fragrance fragrance) {
        fragrance.getSellers().remove(seller);
        fragranceDao.update(fragrance);
    }

    @Override
    public List<Fragrance> getAllBySeller(Long sellerId) {
        return fragranceDao.getAllBySeller(sellerId);
    }

    @Override
    public Fragrance create(Fragrance fragrance) {
        return fragranceDao.create(fragrance);
    }

    @Override
    public Fragrance get(Long id) {
        return fragranceDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get a fragrance by id " + id + ". "));
    }

    @Override
    public List<Fragrance> getAll() {
        return fragranceDao.getAll();
    }

    @Override
    public Fragrance update(Fragrance fragrance) {
        return fragranceDao.update(fragrance);
    }

    @Override
    public boolean delete(Long id) {
        return fragranceDao.delete(id);
    }
}
