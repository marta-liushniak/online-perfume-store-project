package store.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    T create(T t);

    Optional<T> get(Long id);

    List<T> getAll();

    T update(T t);

    boolean delete(Long id);
}
