package service.i_employee;

import model.person.T;

import java.util.Map;

public interface IEmployeeService {
    T findByID(int id);
    Map<Integer, T> findAll(String sortByName);

    Map<Integer, T> find(String name, String customerType, String address, String sortByName);

    boolean delete(int id);

    boolean update(T employee);

    boolean insert(T employee);
}
