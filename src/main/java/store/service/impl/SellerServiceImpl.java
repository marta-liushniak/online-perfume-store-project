package store.service.impl;

import java.util.List;
import java.util.Optional;
import store.dao.SellerDao;
import store.lib.Inject;
import store.lib.Service;
import store.model.Seller;
import store.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
    @Inject
    private SellerDao sellerDao;

    @Override
    public Seller create(Seller seller) {
        return sellerDao.create(seller);
    }

    @Override
    public Seller get(Long id) {
        return sellerDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get a seller by id " + id + ". "));
    }

    @Override
    public List<Seller> getAll() {
        return sellerDao.getAll();
    }

    @Override
    public Seller update(Seller seller) {
        return sellerDao.update(seller);
    }

    @Override
    public boolean delete(Long id) {
        return sellerDao.delete(id);
    }

    @Override
    public Optional<Seller> findByLogin(String login) {
        return sellerDao.findByLogin(login);
    }
}
