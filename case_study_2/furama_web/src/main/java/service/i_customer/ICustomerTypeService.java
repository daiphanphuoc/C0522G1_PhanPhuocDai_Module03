package service.i_customer;

import model.person.CustomerType;

import java.util.Map;

public interface ICustomerTypeService {
    Map<Integer, CustomerType> findAll(String sortByName);

    CustomerType findByID(int id);
}
