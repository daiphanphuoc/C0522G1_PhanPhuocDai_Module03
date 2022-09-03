package repository.business;

import repository.IRepository;

public interface IContractDetailRepository<T> extends IRepository<T> {
    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
