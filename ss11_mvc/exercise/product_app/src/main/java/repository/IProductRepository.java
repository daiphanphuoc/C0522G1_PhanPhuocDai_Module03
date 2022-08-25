package repository;

import java.util.List;

public interface IProductRepository<T> {
    List<T> findByAll();

    void add(T element);

    void edit(String id, T element);

    void delete(String id);

    T findId(String id);


}
