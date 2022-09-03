package repository.impl.facility;

import Util.ConnectionDataBase;
import model.facility.FacilityType;
import model.person.Position;
import repository.facility.IFacilityTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FacilityTypeRepository implements IFacilityTypeRepository<FacilityType>  {

    private static final String SELECT_BY_ID ="select * from facility_type where is_delete =0 and id = ?;";
    private static final String SELECT_ALL = "select * from facility_type where is_delete =0;";

    private static IFacilityTypeRepository<FacilityType> repository;

    private FacilityTypeRepository() {
    }

    public synchronized static IFacilityTypeRepository<FacilityType> getInstance(){
        if(repository==null){
            repository=new FacilityTypeRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, FacilityType> findAll() {
        Map<Integer, FacilityType> typeMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            FacilityType type;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                type = new FacilityType(id, name);
                typeMap.put(id, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeMap;
    }

    @Override
    public FacilityType findByID(int id) {
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new FacilityType(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(FacilityType element) {
        return false;
    }

    @Override
    public boolean insert(FacilityType element) {
        return false;
    }
}
