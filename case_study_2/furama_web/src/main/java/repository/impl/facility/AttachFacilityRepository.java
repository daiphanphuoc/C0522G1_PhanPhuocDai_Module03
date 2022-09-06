package repository.impl.facility;

import Util.ConnectionDataBase;
import model.facility.*;
import model.person.Position;
import repository.facility.IAttachFacilityRepository;
import repository.facility.IFacilityTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AttachFacilityRepository implements IAttachFacilityRepository<AttachFacility> {
    private static final String SELECT_BY_ID = "select * from attach_facility where is_delete =0 and id = ?;";
    private static final String SELECT_ALL = "select * from attach_facility where is_delete =0;";
    private static final String UPDATE = "update attach_facility set `name` = ?, area = ?, cost = ?," +
            " max_people = ? where is_delete =0 and id = ?;";
    private static final String DELETE = "update attach_facility set is_delete = 1  " +
            "where is_delete = 0 and id = ?;";
    private static final String INSERT = "insert into attach_facility(`name`,cost,unit,`status`) " +
            "values(?,?,?,?); ";

    private static IAttachFacilityRepository<AttachFacility> repository;

    private AttachFacilityRepository() {
    }

    public synchronized static IAttachFacilityRepository<AttachFacility> getInstance() {
        if (repository == null) {
            repository = new AttachFacilityRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, AttachFacility> findAll() {
        Map<Integer, AttachFacility> facilityMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            AttachFacility facility;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String unit = rs.getString("unit");
                String status = rs.getString("status");
                double cost = rs.getDouble("cost");
                facility = new AttachFacility(id, name, cost, unit, status);
                facilityMap.put(id, facility);
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
        return facilityMap;
    }

    @Override
    public AttachFacility findByID(int id) {
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String unit = rs.getString("unit");
                String status = rs.getString("status");
                double cost = rs.getDouble("cost");
                return new AttachFacility(id, name, cost, unit, status);
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
            PreparedStatement pre = con.prepareStatement(DELETE);
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
    public boolean update(AttachFacility element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(UPDATE);
            pre.setString(1, element.getName());
            pre.setDouble(2,element.getCost());
            pre.setString(3,element.getUnit());
            pre.setString(4,element.getStatus());
            pre.setInt(5,element.getId());
            return pre.executeUpdate()>0;
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
    public boolean insert(AttachFacility element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(INSERT);
            pre.setString(1, element.getName());
            pre.setDouble(2,element.getCost());
            pre.setString(3,element.getUnit());
            pre.setString(4,element.getStatus());
            return pre.executeUpdate()>0;
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
