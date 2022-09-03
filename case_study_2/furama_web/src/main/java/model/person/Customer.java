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
                    String email, String address, CustomerType customerType) {
        super(name, iDCitizen, birthday, sex, phone, email, address);
        this.customerType = customerType;
    }

    @Override
    public String getInfo() {
        Date date = getBirthday();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString  = df.format(date);
        return String.format("%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s", getName(), getIDCitizen(),dateString,
                isSex(), getPhone(), getEmail(), getAddress(), iDCustomer, customerType.getName());
    }

    public int getiDCustomer() {
        return iDCustomer;
    }

    public void setiDCustomer(int iDCustomer) {
        this.iDCustomer = iDCustomer;
    }

    public Customer(String name, String iDCitizen, Date birthday, boolean sex, String phone,
                    String email, String address, int iDCustomer, CustomerType customerType) {
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
