package repository.employee;

import model.person.Customer;

import java.util.Map;

public interface IEmployeeRepository<T> {
    Map<Integer, T> findAll(String sortByName);

    Map<Integer, T> find(String name, String phone, String address, String sortByName);

    T findByID(int id);

    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
