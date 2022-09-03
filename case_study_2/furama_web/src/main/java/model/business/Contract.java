package model.business;

import java.util.Date;
import java.util.Objects;

public class Contract {

    private int contractID;
    private Date start;
    private Date end;
    private double deposit;
    private double totalMoney;
    private int iDCustomer;
    private int iDEmployee;
    private int iDFacility;

    public Contract() {
    }

    public Contract(int contractID, Date start, Date end, double deposit, double totalMoney, int iDCustomer, int iDEmployee, int iDFacility) {
        this.contractID = contractID;
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.totalMoney = totalMoney;
        this.iDCustomer = iDCustomer;
        this.iDEmployee = iDEmployee;
        this.iDFacility = iDFacility;
    }

    public Contract(int contractID, Date start, Date end, double deposit, int iDCustomer, int iDEmployee, int iDFacility) {
        this.contractID = contractID;
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.iDCustomer = iDCustomer;
        this.iDEmployee = iDEmployee;
        this.iDFacility = iDFacility;
    }

    public Contract(Date start, Date end, double deposit, int iDCustomer, int iDEmployee, int iDFacility) {
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.iDCustomer = iDCustomer;
        this.iDEmployee = iDEmployee;
        this.iDFacility = iDFacility;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getiDCustomer() {
        return iDCustomer;
    }

    public void setiDCustomer(int iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public int getiDEmployee() {
        return iDEmployee;
    }

    public void setiDEmployee(int iDEmployee) {
        this.iDEmployee = iDEmployee;
    }

    public int getiDFacility() {
        return iDFacility;
    }

    public void setiDFacility(int iDFacility) {
        this.iDFacility = iDFacility;
    }
}
