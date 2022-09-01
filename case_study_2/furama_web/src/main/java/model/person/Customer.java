package model.person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Customer extends Person {

    private int iDCustomer;
    private CustomerType customerType;


    public Customer() {
    }

    public Customer(String name, String iDCitizen, Date birthday, boolean sex, String phone,
                    String email, int iDCustomer, CustomerType customerType, String address) {
        super(name, iDCitizen, birthday, sex, phone, email,address);
        this.iDCustomer = iDCustomer;
        this.customerType = customerType;

    }



    public int getIDCustomer() {
        return iDCustomer;
    }

    public void setIDCustomer(int iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }





}
