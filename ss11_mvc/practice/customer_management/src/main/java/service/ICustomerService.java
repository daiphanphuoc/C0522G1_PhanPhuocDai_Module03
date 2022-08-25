package service;

import java.util.List;

public interface ICustomerService<T> {
    List<T> findAll();

    void save(T customer);

    T findById(int id);

    void update(int id, T customer);

    void remove(int id);
}
