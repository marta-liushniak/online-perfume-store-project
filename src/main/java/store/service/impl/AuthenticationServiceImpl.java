package store.service.impl;

import java.util.Optional;
import store.exception.AuthenticationException;
import store.lib.Inject;
import store.lib.Service;
import store.model.Seller;
import store.service.AuthenticationService;
import store.service.SellerService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private SellerService sellerService;

    @Override
    public Seller login(String login, String password) throws AuthenticationException {
        Optional<Seller> seller = sellerService.findByLogin(login);
        if (seller.isPresent() && seller.get().getPassword().equals(password)) {
            return seller.get();
        }
        throw new AuthenticationException("The username or password is incorrect!");
    }
}
