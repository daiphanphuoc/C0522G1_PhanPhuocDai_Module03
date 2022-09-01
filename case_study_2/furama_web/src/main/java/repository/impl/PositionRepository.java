package repository.impl;

import Util.ConnectionDataBase;
import model.person.CustomerType;
import repository.ICustomerTypeRepository;
import repository.IPositionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PositionRepository<Position> implements IPositionRepository{
    private static String SELECT_ALL = "select * from customer_type where is_delete =0;";
    private static IPositionRepository repository;

    private PositionRepository() {
    }

    public static synchronized IPositionRepository getInstance() {
        if (repository == null) {
            repository = new PositionRepository();
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
        }
        return customerTypeMap;
    }
}

