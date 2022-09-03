package service.impl.employee;

import model.person.Division;
import repository.IRepository;
import repository.impl.employee.DivisionRepository;
import service.i_employee.IDivisionService;

import java.util.Map;

public class DivisionService implements IDivisionService<Division> {
    IRepository<Division> repository = DivisionRepository.getInstance();
    private static IDivisionService<Division> service;

    public DivisionService() {
    }

    public static IDivisionService<Division> getInstance() {
        if (service == null) {
            service = new DivisionService();
        }
        return service;
    }
    @Override
    public Map<Integer, Division> findAll() {
        return repository.findAll();
    }

    @Override
    public Division findByID(int id) {
        return repository.findByID(id);
    }
}
