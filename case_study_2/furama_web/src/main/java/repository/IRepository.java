package repository;


import java.util.Map;

public interface IRepository<T> {
    Map<Integer, T> findAll();

    T findByID(int id);
}
