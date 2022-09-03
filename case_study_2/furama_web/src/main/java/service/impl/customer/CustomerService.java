package service.impl.customer;

import model.person.Customer;
import repository.customer.ICustomerRepository;
import repository.impl.customer.CustomerRepository;
import service.i_customer.ICustomerService;

import java.util.Map;

public class CustomerService implements ICustomerService {
    ICustomerRepository repository = CustomerRepository.getInstance();
    private static ICustomerService customerService;

    private CustomerService() {
    }

    public synchronized static ICustomerService getInstance() {
        if (customerService==null){
            customerService=new CustomerService();
        }
        return customerService;
    }

    @Override
    public Map<Integer, Customer> findAll(String sortByName) {
        return repository.findAll(sortByName);
    }

    @Override
    public Map<Integer, Customer> find(String name, String customerType, String address, String sortByName) {
        return repository.find(name, customerType, address, sortByName);
    }

    @Override
    public Customer findByID(int id) {
        return repository.findByID(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(Customer customer) {
        return repository.update(customer);
    }

    @Override
    public boolean insert(Customer customer) {
        return repository.insert(customer);
    }
}
