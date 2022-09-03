package repository.facility;

import java.util.Map;

public interface IFacilityRepository<T> {
    Map<Integer, T> findAll(String sortByName);

    Map<Integer, T> find(String name, String facilityType, String rentType, String sortByName);

    T findByID(int id);

    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);

}
