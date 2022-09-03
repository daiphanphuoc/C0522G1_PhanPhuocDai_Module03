package repository.facility;

import repository.IRepository;

public interface IFacilityTypeRepository<T> extends IRepository<T> {
    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
