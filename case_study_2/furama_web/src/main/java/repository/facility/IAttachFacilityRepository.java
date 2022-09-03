package repository.facility;

import repository.IRepository;

public interface IAttachFacilityRepository<T> extends IRepository<T> {
    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
