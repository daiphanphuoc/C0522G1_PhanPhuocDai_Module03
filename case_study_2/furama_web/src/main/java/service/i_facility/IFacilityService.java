package service.i_facility;

import java.util.Map;

public interface IFacilityService<T> {
    T findByID(int id);
    Map<Integer, T> findAll(String sortByName);

    Map<Integer, T> find(String name, String facilityType, String rentType, String sortByName);

    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
