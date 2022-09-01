package service;

import model.person.Customer;

import java.util.Map;

public interface ICustomerService {
    Map<Integer, Customer> findAll(String sortByName);

    Map<Integer,Customer> find(String name, String customerType, String address, String sortByName);

    Customer findByID(int id);

    boolean delete(int id);

    boolean update(Customer customer);

    boolean insert(Customer customer);
}
