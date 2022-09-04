package service.impl.employee;

import model.person.Employee;
import repository.employee.IEmployeeRepository;
import repository.impl.employee.EmployeeRepository;
import service.i_employee.IEmployeeService;

import java.util.Map;

public class EmployeeService implements IEmployeeService {
    IEmployeeRepository<Employee> repository = EmployeeRepository.getInstance();
    private static IEmployeeService service;

    public EmployeeService() {
    }

    public static IEmployeeService getInstance() {
        if (service == null) {
            service = new EmployeeService();
        }
        return service;
    }

    @Override
    public Employee findByID(int id) {
        return repository.findByID(id);
    }

    @Override
    public Map<Integer, Employee> findAll(String sortByName) {
        return repository.findAll(sortByName);
    }

    @Override
    public Map<Integer, Employee> find(String name, String phone, String address, String sortByName) {
        return repository.find(name,phone,address,sortByName);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(Employee employee) {
        return repository.update(employee);
    }

    @Override
    public boolean insert(Employee employee) {
        return repository.insert(employee);
    }
}
