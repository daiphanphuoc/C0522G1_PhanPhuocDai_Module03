package service.impl.employee;

import model.person.Division;
import model.person.Position;
import repository.IRepository;
import repository.impl.employee.DivisionRepository;
import repository.impl.employee.PositionRepository;
import service.i_employee.IDivisionService;
import service.i_employee.IPositionService;

import java.util.Map;

public class PositionService implements IPositionService<Position> {
    IRepository<Position> repository = PositionRepository.getInstance();
    private static IPositionService<Position> service;

    public PositionService() {
    }

    public static IPositionService<Position> getInstance() {
        if (service == null) {
            service = new PositionService();
        }
        return service;
    }
    @Override
    public Map<Integer, Position> findAll() {
        return repository.findAll();
    }

    @Override
    public Position findByID(int id) {
        return repository.findByID(id);
    }
}
