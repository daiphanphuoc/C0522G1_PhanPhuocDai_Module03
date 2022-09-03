package service.impl.employee;

import model.person.EducationDegree;
import repository.IRepository;
import repository.impl.employee.EducationDegreeRepository;
import service.i_employee.IEducationDegreeService;

import java.util.Map;

public class EducationDegreeService implements IEducationDegreeService<EducationDegree> {
    IRepository<EducationDegree> repository = EducationDegreeRepository.getInstance();
    private static IEducationDegreeService<EducationDegree> service;

    public EducationDegreeService() {
    }

    public static IEducationDegreeService<EducationDegree> getInstance() {
        if (service == null) {
            service = new EducationDegreeService();
        }
        return service;
    }

    @Override
    public Map<Integer, EducationDegree> findAll() {
        return repository.findAll();
    }

    @Override
    public EducationDegree findByID(int id) {
        return repository.findByID(id);
    }
}
