package service.impl.facility;

import model.facility.FacilityType;
import model.facility.RentType;
import repository.IRepository;
import repository.facility.IFacilityTypeRepository;
import repository.impl.facility.FacilityTypeRepository;
import repository.impl.facility.RentTypeRepository;
import service.i_facility.IFacilityTypeService;
import service.i_facility.IRentTypeService;

import java.util.Map;

public class FacilityTypeService implements IFacilityTypeService<FacilityType> {

    private static IFacilityTypeService<FacilityType> service;

    private FacilityTypeService() {
    }

    public static IFacilityTypeService<FacilityType> getInstance() {
        if (service == null) {
            service = new FacilityTypeService();
        }
        return service;
    }
    @Override
    public Map<Integer, FacilityType> findAll() {
        return FacilityTypeRepository.getInstance().findAll();
    }

    @Override
    public FacilityType findByID(int id) {
        return FacilityTypeRepository.getInstance().findByID(id);
    }
}
