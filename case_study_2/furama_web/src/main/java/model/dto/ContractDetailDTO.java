package model.dto;

public class ContractDetailDTO {

    private  int idContract;
    private  int idDetail;
    private  int idAttach;
    private String name;
    private double cost;
    private int quantity;
    private String unit;
    private String status;

    public ContractDetailDTO() {
    }

    public ContractDetailDTO(int idContract, int idDetail, int idAttach, String name,
                             double cost, int quantity, String unit, String status) {
        this.idContract = idContract;
        this.idDetail = idDetail;
        this.idAttach = idAttach;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
    }

    public int getIdContract() {
        return idContract;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public int getIdAttach() {
        return idAttach;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getStatus() {
        return status;
    }
}
