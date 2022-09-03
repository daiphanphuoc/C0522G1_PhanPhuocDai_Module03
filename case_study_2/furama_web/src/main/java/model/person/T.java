package model.person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class T extends Person {
    private int iDEmployee;
    private EducationDegree degree;
    private Position position;
    private Division division;
    private double salary;
    private String userName;

    public T() {
    }

    @Override
    public String getInfo() {
        Date date = getBirthday();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString  = df.format(date);
        return String.format("%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s@@%s", getName(), getIDCitizen(),dateString,
                isSex(), getPhone(), getEmail(), getAddress(), iDEmployee, degree.getName(),
                position.getName(),division.getName(), salary, userName);
    }

    public T(String name, String iDCitizen, Date birthday, boolean sex,
             String phone, String email, String address, int iDEmployee,
             EducationDegree degree, Position position, Division division,
             double salary, String userName) {
        super(name, iDCitizen, birthday, sex, phone, email, address);
        this.iDEmployee = iDEmployee;
        this.degree = degree;
        this.position = position;
        this.division = division;
        this.salary = salary;
        this.userName = userName;
    }


    public EducationDegree getDegree() {
        return degree;
    }

    public void setDegree(EducationDegree degree) {
        this.degree = degree;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getIDEmployee() {
        return iDEmployee;
    }

    public void setIDEmployee(int iDEmployee) {
        this.iDEmployee = iDEmployee;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
