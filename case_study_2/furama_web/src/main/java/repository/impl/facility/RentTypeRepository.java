package repository.impl.facility;

import Util.ConnectionDataBase;
import model.facility.RentType;
import model.person.Position;
import repository.IRepository;
import repository.facility.IRentTypeRepository;
import repository.impl.employee.PositionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RentTypeRepository implements IRentTypeRepository<RentType> {
    private static final String SELECT_BY_ID ="select * from rent_type where is_delete =0 and id = ?;";
    private static final String SELECT_ALL = "select * from `rent_type` where is_delete =0;";

    private static IRepository<RentType> repository;

    private RentTypeRepository() {
    }

    public static synchronized IRepository<RentType> getInstance() {
        if (repository == null) {
            repository = new RentTypeRepository();
        }
        return repository;
    }
    @Override
    public Map<Integer, RentType> findAll() {
        Map<Integer, RentType> rentTypeMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            RentType rentType;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                rentType = new RentType(id, name);
                rentTypeMap.put(id, rentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentTypeMap;
    }

    @Override
    public RentType findByID(int id) {
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new RentType(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
