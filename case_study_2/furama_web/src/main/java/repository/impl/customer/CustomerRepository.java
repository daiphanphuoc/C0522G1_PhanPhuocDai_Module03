package repository.impl.customer;

import Util.ConnectionDataBase;
import model.person.Customer;
import model.person.CustomerType;
import repository.customer.ICustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    private static final String SELECT_BY_ID = "select customer.*, customer_type.name as customer_type_name " +
            "from customer join customer_type on customer.customer_type_id = customer_type.id " +
            "where customer.is_delete = 0 and customer.id = ?;";
    private static final String SELECT_ALL = "select customer.*, customer_type.name as customer_type_name " +
            "from customer join customer_type on customer.customer_type_id = customer_type.id " +
            "where customer.is_delete = 0;";
    private static final String SELECT_BY_SEARCH = "select customer.*, customer_type.name as customer_type_name " +
            "from customer join customer_type on customer.customer_type_id = customer_type.id " +
            "            where customer.is_delete = 0 and customer.name like ? and customer.address like ? " +
            "            and customer_type.name like ?;";
    private static final String DELETE_CUSTOMER = "update customer set is_delete = 1 where is_delete = 0 and id = ?;";
    private static final String UPDATE_CUSTOMER = "update customer " +
            "set customer_type_id = ?, name = ?, gender = ?, date_of_birth = ?, id_card = ?, phone_number = ?," +
            " email = ?, address = ?  where id = ? and is_delete = 0; ";
    private static final String INSERT_CUSTOMER = "insert into customer( name,date_of_birth,gender,id_card,phone_number,email,address,customer_type_id) " +
            "values (?,?,?,?,?,?,?,?)";

    private static ICustomerRepository repository;

    private CustomerRepository() {
    }

    public synchronized static ICustomerRepository getInstance() {
        if (repository == null) {
            repository = new CustomerRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, Customer> findAll(String sortByName) {
        Map<Integer, Customer> customers = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {

            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            Customer customer;
            CustomerType customerType;
            while (rs.next()) {
                int idType = rs.getInt("customer_type_id");
                String nameType = rs.getString("customer_type_name");
                customerType = new CustomerType(idType, nameType);
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                customer = new Customer(name, idCard, birthday, gender, phone, email, address, id, customerType);
                customers.put(id, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public Map<Integer, Customer> find(String nameCustomer,  String customerTypeName,String addressCustomer, String sortByName) {
        Map<Integer, Customer> customers = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {

            PreparedStatement pre = con.prepareStatement(SELECT_BY_SEARCH);
            pre.setString(1, "%"+(nameCustomer==null?"":nameCustomer)+"%");
            pre.setString(2, "%"+(addressCustomer==null?"":addressCustomer)+"%");
            pre.setString(3, "%"+(customerTypeName==null?"":customerTypeName)+"%");
            ResultSet rs = pre.executeQuery();
            Customer customer;
            CustomerType customerType;
            while (rs.next()) {
                int idType = rs.getInt("customer_type_id");
                String nameType = rs.getString("customer_type_name");
                customerType = new CustomerType(idType, nameType);
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");
                customer = new Customer(name, idCard, birthday, gender, phone, email, address, id, customerType);
                customers.put(id, customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public Customer findByID(int id) {
        Customer customer;
        Connection con = ConnectionDataBase.getConnection();
        try {

            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1, id);

            ResultSet rs = pre.executeQuery();
            CustomerType customerType;
            if (rs.next()) {
                int idType = rs.getInt("customer_type_id");
                String nameType = rs.getString("customer_type_name");
                customerType = new CustomerType(idType, nameType);

                String name = rs.getString("name");
                boolean gender = rs.getBoolean("gender");
                Date birthday = rs.getDate("date_of_birth");
                String idCard = rs.getString("id_card");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String address = rs.getString("address");

                customer = new Customer(name, idCard, birthday, gender, phone, email, address, id, customerType);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(DELETE_CUSTOMER);
            pre.setInt(1, id);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(UPDATE_CUSTOMER);
            pre.setInt(1, customer.getCustomerType().getId());
            pre.setString(2, customer.getName());
            pre.setBoolean(3, customer.isSex());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(customer.getBirthday());
            pre.setString(4, date);
            pre.setString(5, customer.getIDCitizen());
            pre.setString(6, customer.getPhone());
            pre.setString(7, customer.getEmail());
            pre.setString(8, customer.getAddress());
            pre.setInt(9, customer.getIDCustomer());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean insert(Customer customer) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(INSERT_CUSTOMER);
            //name,date_of_birth,gender,id_card,phone_number,email,address,customer_type_id
            pre.setString(1, customer.getName());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(customer.getBirthday());
            pre.setString(2, date);
            //pre.setDate(2, (java.sql.Date) customer.getBirthday());
            pre.setBoolean(3, customer.isSex());
            pre.setString(4, customer.getIDCitizen());
            pre.setString(5, customer.getPhone());
            pre.setString(6, customer.getEmail());
            pre.setString(7, customer.getAddress());
            pre.setInt(8, customer.getCustomerType().getId());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
