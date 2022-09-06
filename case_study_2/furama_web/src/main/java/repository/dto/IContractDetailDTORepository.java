package repository.dto;

import repository.IRepository;

import java.util.Map;

public interface IContractDetailDTORepository<T> extends IRepository<T> {
    Map<Integer, T> find(int idContract);
}
