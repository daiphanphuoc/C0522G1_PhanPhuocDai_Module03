package service;

import java.util.Map;

public interface IOService<T> {
    Map<Integer, T> findAll();

    Map<Integer, T> find(String search);

    Map<Integer, T> findNotID(int id);

    T findID(int id);

    boolean insert(T element);

    boolean update(T element);

    boolean delete(int id);
}
