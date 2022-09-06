package repository.impl.customer;

import Util.ConnectionDataBase;
import model.person.CustomerType;
import repository.IRepository;
import repository.customer.ICustomerTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerTypeRepository implements ICustomerTypeRepository<CustomerType> {
    private static String SELECT_ALL = "select * from customer_type where is_delete = 0;";
    private static String SELECT_BY_ID = "select * from customer_type where is_delete = 0 and id = ? ;";
    private static ICustomerTypeRepository<CustomerType> repository;

    private CustomerTypeRepository() {
    }

    public static synchronized IRepository<CustomerType> getInstance() {
        if (repository == null) {
            repository = new CustomerTypeRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, CustomerType> findAll() {
        Map<Integer, CustomerType> customerTypeMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            CustomerType customerType;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                customerType = new CustomerType(id, name);
                customerTypeMap.put(id, customerType);
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
        return customerTypeMap;
    }

    @Override
    public CustomerType findByID(int id) {
        CustomerType customerType =null;
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                customerType = new CustomerType(id, name);
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
        return customerType;
    }
}
