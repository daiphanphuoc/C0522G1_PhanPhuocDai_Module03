package service;

import java.util.Map;

public interface IService<T> {
    Map<Integer, T> findAll();

    T findByID(int id);
}
