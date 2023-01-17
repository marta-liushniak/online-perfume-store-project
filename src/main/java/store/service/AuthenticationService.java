package store.service;

import store.exception.AuthenticationException;
import store.model.Seller;

public interface AuthenticationService {
    Seller login(String login, String password) throws AuthenticationException;
}
