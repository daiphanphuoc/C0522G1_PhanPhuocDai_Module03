package service.impl.customer;

import model.person.CustomerType;
import repository.IRepository;
import repository.impl.customer.CustomerTypeRepository;
import service.i_customer.ICustomerTypeService;

import java.util.Map;

public class CustomerTypeService implements ICustomerTypeService {
    IRepository<CustomerType> repository = CustomerTypeRepository.getInstance();
    private static ICustomerTypeService customerService;

    private CustomerTypeService() {
    }

    public synchronized static ICustomerTypeService getInstance() {
        if (customerService == null) {
            customerService = new CustomerTypeService();
        }
        return customerService;
    }

    @Override
    public Map<Integer, CustomerType> findAll(String sortByName) {
        return repository.findAll();
    }

    @Override
    public CustomerType findByID(int id) {
        return repository.findByID(id);
    }
}
