package service.i_employee;

import model.person.Employee;

import java.util.Map;

public interface IEmployeeService {
    Employee findByID(int id);
    Map<Integer, Employee> findAll(String sortByName);

    Map<Integer, Employee> find(String name, String phone, String address, String sortByName);

    boolean delete(int id);

    boolean update(Employee employee);

    boolean insert(Employee employee);
}
