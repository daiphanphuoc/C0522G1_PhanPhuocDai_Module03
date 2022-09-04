package service.impl.facility;

import model.facility.AttachFacility;
import repository.facility.IAttachFacilityRepository;
import repository.impl.facility.AttachFacilityRepository;
import service.i_facility.IAttachFacilityService;

import java.util.Map;

public class AttachFacilityService implements IAttachFacilityService<AttachFacility> {
    IAttachFacilityRepository<AttachFacility> repository = AttachFacilityRepository.getInstance();
    private static IAttachFacilityService<AttachFacility> service;

    private AttachFacilityService() {
    }

    public static IAttachFacilityService<AttachFacility> getInstance() {
        if (service == null) {
            service = new AttachFacilityService();
        }
        return service;
    }

    @Override
    public Map<Integer, AttachFacility> findAll() {
        return repository.findAll();
    }

    @Override
    public AttachFacility findByID(int id) {
        return repository.findByID(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(AttachFacility element) {
        return repository.update(element);
    }

    @Override
    public boolean insert(AttachFacility element) {
        return repository.insert(element);
    }
}
