package model.business;

public class ContractDetail {
    private int id;
    private int contractId;
    private int attachFacilityId;
    private int quantity;

    public ContractDetail() {
    }

    public ContractDetail(int id, int contractId, int attachFacilityId, int quantity) {
        this.id = id;
        this.contractId = contractId;
        this.attachFacilityId = attachFacilityId;
        this.quantity = quantity;
    }

    public ContractDetail(int contractId, int attachFacilityId, int quantity) {
        this.contractId = contractId;
        this.attachFacilityId = attachFacilityId;
        this.quantity = quantity;
    }
}
