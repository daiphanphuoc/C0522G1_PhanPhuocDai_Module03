package service.impl.dto;

import model.dto.ContractDetailDTO;
import repository.dto.IContractDetailDTORepository;
import repository.impl.dto.ContractDetailDTORepository;
import service.dto.IContractDetailDTOService;

import java.util.Map;

public class ContractDetailDTOService implements IContractDetailDTOService<ContractDetailDTO> {
    private static IContractDetailDTORepository<ContractDetailDTO> repository = ContractDetailDTORepository.getInstance();
    private static IContractDetailDTOService<ContractDetailDTO> service;

    private ContractDetailDTOService() {
    }

    public synchronized static IContractDetailDTOService<ContractDetailDTO> getInstance() {
        if (service == null) {
            service = new ContractDetailDTOService();
        }
        return service;
    }

    @Override
    public Map<Integer, ContractDetailDTO> findAll() {
        return repository.findAll();
    }

    @Override
    public Map<Integer, ContractDetailDTO> find(int idContract) {
        return repository.find(idContract);
    }

    @Override
    public ContractDetailDTO findByID(int id) {
        return repository.findByID(id);
    }
}
