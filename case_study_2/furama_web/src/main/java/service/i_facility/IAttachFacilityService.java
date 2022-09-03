package service.i_facility;

import service.IService;

public interface IAttachFacilityService<T> extends IService<T> {
    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
