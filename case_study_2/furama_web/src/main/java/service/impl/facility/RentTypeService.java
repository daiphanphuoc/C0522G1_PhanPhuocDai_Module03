package service.impl.facility;

import model.facility.Facility;
import model.facility.RentType;
import repository.IRepository;
import repository.facility.IFacilityRepository;
import repository.facility.IRentTypeRepository;
import repository.impl.facility.FacilityRepository;
import repository.impl.facility.RentTypeRepository;
import service.i_facility.IFacilityService;
import service.i_facility.IRentTypeService;

import java.util.Map;

public class RentTypeService implements IRentTypeService<RentType> {
    IRepository<RentType> repository = RentTypeRepository.getInstance();
    private static IRentTypeService<RentType> service;

    private RentTypeService() {
    }

    public static IRentTypeService<RentType> getInstance() {
        if (service == null) {
            service = new RentTypeService();
        }
        return service;
    }
    @Override
    public Map<Integer, RentType> findAll() {
        return repository.findAll();
    }

    @Override
    public RentType findByID(int id) {
        return repository.findByID(id);
    }
}
