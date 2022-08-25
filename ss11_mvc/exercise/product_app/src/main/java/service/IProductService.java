package service;

import java.util.List;

public interface IProductService<T> {
    List<T> searchByName(String name);

    List<T> findByAll();

    void add(T element);

    void edit(String id, T element);

    void delete(String id);

    T findId(String id);
}
