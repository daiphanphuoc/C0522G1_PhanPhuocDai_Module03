package service.impl.business;

import model.business.ContractDetail;
import repository.business.IContractDetailRepository;
import repository.impl.business.ContractDetailRepository;
import service.business.IContractDetailService;

import java.util.Map;

public class ContractDetailService implements IContractDetailService<ContractDetail> {
    IContractDetailRepository<ContractDetail> repository = ContractDetailRepository.getInstance();
    private static IContractDetailService<ContractDetail> service;

    private ContractDetailService() {
    }

    public synchronized static IContractDetailService<ContractDetail> getInstance() {
        if (service == null) {
            service = new ContractDetailService();
        }
        return service;
    }


    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(ContractDetail element) {
        return repository.update(element);
    }

    @Override
    public boolean insert(ContractDetail element) {
        return repository.insert(element);
    }

    @Override
    public Map<Integer, ContractDetail> findAll() {
        return repository.findAll();
    }

    @Override
    public ContractDetail findByID(int id) {
        return repository.findByID(id);
    }
}
