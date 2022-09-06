package service.business;

import service.IService;

public interface IContractDetailService<T> extends IService<T> {
    boolean delete(int id);

    boolean update(T element);

    boolean insert(T element);
}
