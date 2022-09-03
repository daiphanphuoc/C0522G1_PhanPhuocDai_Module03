package service.impl.facility;

import model.facility.Facility;
import repository.facility.IFacilityRepository;
import repository.impl.facility.FacilityRepository;
import service.i_facility.IFacilityService;

import java.util.Map;

public class FacilityService implements IFacilityService<Facility> {
    IFacilityRepository<Facility> repository = FacilityRepository.getInstance();
    private static IFacilityService<Facility> service;

    public FacilityService() {
    }

    public static IFacilityService<Facility> getInstance() {
        if (service == null) {
            service = new FacilityService();
        }
        return service;
    }

    @Override
    public Facility findByID(int id) {
        return repository.findByID(id);
    }

    @Override
    public Map<Integer, Facility> findAll(String sortByName) {
        return repository.findAll(sortByName);
    }

    @Override
    public Map<Integer, Facility> find(String name, String facilityType, String rentType, String sortByName) {
        return repository.find(name,facilityType,rentType,sortByName);
    }


    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(Facility element) {
        return repository.update(element);
    }

    @Override
    public boolean insert(Facility element) {
        return repository.insert(element);
    }
}
