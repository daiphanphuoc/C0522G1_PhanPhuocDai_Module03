package service.impl.business;

import model.business.Contract;
import repository.IRepository;
import repository.business.IContractRepository;
import repository.impl.business.ContractRepository;
import service.business.IContractService;

import java.util.Map;

public class ContractService implements IContractService<Contract> {
    IContractRepository<Contract> repository = ContractRepository.getInstance();
    private static IContractService<Contract> service;

    private ContractService() {
    }

    public synchronized static IContractService<Contract> getInstance() {
        if (service == null) {
            service = new ContractService();
        }
        return service;
    }
    @Override
    public Map<Integer, Contract> findAll() {
        return repository.findAll();
    }

    @Override
    public Contract findByID(int id) {
        return repository.findByID(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public boolean update(Contract element) {
        return repository.update(element);
    }

    @Override
    public boolean insert(Contract element) {
        return repository.insert(element);
    }
}
