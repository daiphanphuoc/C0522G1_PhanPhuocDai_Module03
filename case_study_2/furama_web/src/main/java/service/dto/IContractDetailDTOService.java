package service.dto;

import java.util.Map;

public interface IContractDetailDTOService<T> {
    Map<Integer, T> findAll();
    Map<Integer, T> find(int id);

    T findByID(int id);

}
