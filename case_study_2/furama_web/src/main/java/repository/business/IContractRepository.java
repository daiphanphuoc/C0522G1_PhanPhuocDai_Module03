package repository.business;

import repository.IRepository;

public interface IContractRepository<T> extends IRepository<T> {
    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
