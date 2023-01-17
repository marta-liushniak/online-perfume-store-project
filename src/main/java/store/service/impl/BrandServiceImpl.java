package store.service.impl;

import java.util.List;
import store.dao.BrandDao;
import store.lib.Inject;
import store.lib.Service;
import store.model.Brand;
import store.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    @Inject
    private BrandDao brandDao;

    @Override
    public Brand create(Brand brand) {
        return brandDao.create(brand);
    }

    @Override
    public Brand get(Long id) {
        return brandDao.get(id).orElseThrow(
                () -> new RuntimeException("Can't get a brand by id " + id + ". "));
    }

    @Override
    public List<Brand> getAll() {
        return brandDao.getAll();
    }

    @Override
    public Brand update(Brand brand) {
        return brandDao.update(brand);
    }

    @Override
    public boolean delete(Long id) {
        return brandDao.delete(id);
    }
}
