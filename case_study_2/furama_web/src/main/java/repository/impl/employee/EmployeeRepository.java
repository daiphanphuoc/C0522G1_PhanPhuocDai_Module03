package repository.impl.employee;

import Util.ConnectionDataBase;
import model.person.*;
import org.jetbrains.annotations.NotNull;
import repository.employee.IEmployeeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeeRepository implements IEmployeeRepository<Employee> {
    private static final String DELETE = "update employee set is_delete = 1 where is_delete = 0 and id = ?;";
    private static final String SELECT_BY_ID = "select employee.*, position.name as position_name," +
            " division.name as division_name, education_degree.name as education_degree_name " +
            "from employee " +
            "join position on position_id=position.id " +
            "join division on division_id=division.id " +
            "join education_degree on education_degree_id=education_degree.id " +
            "where employee.is_delete = 0 and employee.id = ? ;";
    private static final String SELECT = "select employee.*, position.name as position_name, " +
            "division.name as division_name, education_degree.name as education_degree_name " +
            "from employee " +
            "join position on position_id=position.id " +
            "join division on division_id=division.id " +
            "join education_degree on education_degree_id=education_degree.id " +
            "where employee.is_delete = 0 ;";
    private static final String SELECT_SEARCH = "select employee.*, position.name as position_name, " +
            "division.name as division_name, education_degree.name as education_degree_name " +
            "from employee " +
            "join position on position_id=position.id " +
            "join division on division_id=division.id " +
            "join education_degree on education_degree_id=education_degree.id " +
            "where employee.is_delete = 0 and employee.name like ? and phone_number like ? and address like ? ;";
    private static final String INSERT = "insert into employee(name, date_of_birth, " +
            "id_card, salary, phone_number, email, address, " +
            "position_id, education_degree_id,division_id,gender)" +
            "values(?,?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "update  employee set name =?, date_of_birth = ?, " +
            " id_card = ?, salary =?, phone_number =? , email = ?, address =?, position_id = ?, " +
            "education_degree_id = ?, division_id = ?, gender = ? where id = ? and is_delete = 0;";

    private static IEmployeeRepository<Employee> repository;

    public EmployeeRepository() {
    }

    public synchronized static IEmployeeRepository<Employee> getInstance() {
        if (repository == null) {
            repository = new EmployeeRepository();

        }
        return repository;
    }

    @Override
    public Map<Integer, Employee> findAll(String sortByName) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Employee employee;
        try {
            Connection con = ConnectionDataBase.getConnection();
            PreparedStatement pre = con.prepareStatement(SELECT);


            ResultSet rs = pre.executeQuery();
            Position position;
            EducationDegree degree;
            Division division;
            while (rs.next()) {
                int idP = rs.getInt("position_id");
                String nameP = rs.getString("position_name");
                position = new Position(idP, nameP);
                int idDegree = rs.getInt("education_degree_id");
                String nameDegree = rs.getString("education_degree_name");
                degree = new EducationDegree(idDegree, nameDegree);
                int idDivision = rs.getInt("division_id");
                String nameDivision = rs.getString("division_name");
                division = new Division(idDivision, nameDivision);

                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                double salary = rs.getDouble("salary");
                String user = rs.getNString("user_name");

                employee = new Employee(name, idCard, birthday, gender, phone, email, address, id, degree, position, division, salary, user);
                employeeMap.put(id, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeMap;
    }

    @Override
    public Map<Integer, Employee> find(String name, String phone, String address, String sortByName) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        Employee employee;
        try {
            Connection con = ConnectionDataBase.getConnection();
            PreparedStatement pre = con.prepareStatement(SELECT_SEARCH);
            pre.setString(1, "%" + (name==null?"":name) + "%");
            pre.setString(2, "%" + (phone==null?"":phone) + "%");
            pre.setString(3, "%" + (address==null?"":address) + "%");

            ResultSet rs = pre.executeQuery();
            Position position;
            EducationDegree degree;
            Division division;
            while (rs.next()) {
                int idP = rs.getInt("position_id");
                String nameP = rs.getString("position_name");
                position = new Position(idP, nameP);

                int idDegree = rs.getInt("education_degree_id");
                String nameDegree = rs.getString("education_degree_name");
                degree = new EducationDegree(idDegree, nameDegree);

                int idDivision = rs.getInt("division_id");
                String nameDivision = rs.getString("division_name");
                division = new Division(idDivision, nameDivision);

                String nameEmployee = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                String phoneEmployee = rs.getString("phone_number");
                String email = rs.getString("email");
                String addressEmployee = rs.getString("address");
                double salary = rs.getDouble("salary");
                String user = rs.getNString("user_name");
                int id = rs.getInt("id");
                employee = new Employee(nameEmployee, idCard, birthday, gender, phoneEmployee, email, addressEmployee, id, degree, position, division, salary, user);
                employeeMap.put(id, employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeMap;
    }

    @Override
    public Employee findByID(int id) {
        Employee employee;
        try {
            Connection con = ConnectionDataBase.getConnection();
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();
            Position position;
            EducationDegree degree;
            Division division;
            if (rs.next()) {
                int idP = rs.getInt("position_id");
                String nameP = rs.getString("position_name");
                position = new Position(idP, nameP);
                int idDegree = rs.getInt("education_degree_id");
                String nameDegree = rs.getString("education_degree_name");
                degree = new EducationDegree(idDegree, nameDegree);
                int idDivision = rs.getInt("division_id");
                String nameDivision = rs.getString("division_name");
                division = new Division(idDivision, nameDivision);

                String name = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                double salary = rs.getDouble("salary");
                String user = rs.getNString("user_name");

                employee = new Employee(name, idCard, birthday, gender, phone, email, address, id, degree, position, division, salary, user);
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(DELETE);
            pre.setInt(1, id);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(@NotNull Employee employee) {
        Connection connection = ConnectionDataBase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, employee.getName());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(employee.getBirthday());
            preparedStatement.setString(2, date);
            /*preparedStatement.setString(2, employee.getBirthday());*/
            preparedStatement.setString(3, employee.getIDCitizen());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setInt(8, employee.getPosition().getId());
            preparedStatement.setInt(9, employee.getDegree().getId());
            preparedStatement.setInt(10, employee.getDivision().getId());
            preparedStatement.setBoolean(11, employee.isSex());
            preparedStatement.setInt(12, employee.getIDEmployee());
            int num = preparedStatement.executeUpdate();
            return (num == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(@NotNull Employee employee) {
        Connection connection = ConnectionDataBase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, employee.getName());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(employee.getBirthday());
            preparedStatement.setString(2, date);
            /*preparedStatement.setString(2, employee.getBirthday());*/
            preparedStatement.setString(3, employee.getIDCitizen());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setInt(8, employee.getPosition().getId());
            preparedStatement.setInt(9, employee.getDegree().getId());
            preparedStatement.setInt(10, employee.getDivision().getId());
            preparedStatement.setBoolean(11, employee.isSex());
            int num = preparedStatement.executeUpdate();
            return (num == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
