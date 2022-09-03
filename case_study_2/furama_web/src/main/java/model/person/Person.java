package model.person;

import java.util.Date;

public abstract class Person {
    private String name;
    private String iDCitizen;
    private Date birthday;
    private boolean sex;
    private String phone;
    private String email;
    private String address;

    public Person() {
    }

    public Person(String name, String iDCitizen, Date birthday, boolean sex, String phone, String email, String address) {
        this.name = name;
        this.iDCitizen = iDCitizen;
        this.birthday = birthday;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public abstract String getInfo();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCitizen() {
        return iDCitizen;
    }

    public void setIDCitizen(String iDCitizen) {
        this.iDCitizen = iDCitizen;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "name='" + name +
                "', iDCitizen='" + iDCitizen +
                "', birthday='" + birthday +
                "', sex=" + (sex ? "'Nam'" : "'Nữ'") +
                "', phone='" + phone +
                "', email='" + email +
                "', address='" + address + '\'';
    }
}
