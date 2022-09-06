package repository.impl.employee;

import Util.ConnectionDataBase;
import model.person.CustomerType;
import model.person.Division;
import repository.IRepository;
import repository.employee.IDivisionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DivisionRepository implements IDivisionRepository<Division> {
    private static String SELECT_ALL = "select * from division where is_delete =0;";
    private static String SELECT_BY_ID = "select * from division where is_delete =0 and id =?;";

    private static IDivisionRepository<Division> repository;

    private DivisionRepository() {
    }

    public static synchronized IDivisionRepository<Division> getInstance() {
        if (repository == null) {
            repository = new DivisionRepository();
        }
        return repository;
    }

    public Map<Integer, Division> findAll() {
        Map<Integer, Division> divisionMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            Division division;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                division = new Division(id, name);
                divisionMap.put(id, division);
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
        return divisionMap;
    }

    @Override
    public Division findByID(int id) {
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new Division(id, name);
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
}
