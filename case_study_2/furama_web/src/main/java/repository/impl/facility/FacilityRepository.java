package repository.impl.facility;

import Util.ConnectionDataBase;
import model.facility.*;
import repository.facility.IFacilityRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FacilityRepository implements IFacilityRepository<Facility> {
    private static final String SELECT_BY_ID = "select facility.*,facility_type.name as facility_type_name,rent_type.name as rent_type_name " +
            "from facility " +
            "inner join facility_type on facility_type_id=facility_type.id " +
            "inner join rent_type on rent_type_id=rent_type.id " +
            "where facility.is_delete = 0 and facility.id = ?;";;
    private static final String DELETE_FACILITY = "update facility set is_delete = 1 where is_delete = 0 and id = ?;";
    private static final String SELECT_ALL = "select facility.*,facility_type.name as facility_type_name,rent_type.name as rent_type_name " +
            "from facility " +
            "inner join facility_type on facility_type_id=facility_type.id " +
            "inner join rent_type on rent_type_id=rent_type.id " +
            "where facility.is_delete = 0;";
    private static final String SELECT_SEARCH = "select facility.*,facility_type.name as facility_type_name,rent_type.name as rent_type_name " +
            "from facility " +
            "inner join facility_type on facility_type_id=facility_type.id " +
            "inner join rent_type on rent_type_id=rent_type.id " +
            "where facility.is_delete = 0 and facility.name like ? and facility_type.name like ? and rent_type.name like ?;";
    
    private static final String INSERT = "insert into facility(`name`, area, cost, max_people, " +
            "standard_room, description_other_convenience, pool_area, number_of_floors, " +
            "facility_free, rent_type_id,facility_type_id) " +
            "values (?,?,?,?,?,?,?,?,?,?,?);";
    private static final String UPDATE = "update facility set `name` = ?, area = ?, cost = ?, " +
            " max_people = ?,standard_room= ?,description_other_convenience = ?,pool_area = ?, " +
            " number_of_floors = ?,facility_free = ?,rent_type_id = ?,facility_type_id = ? " +
            " where is_delete = 0 and id = ?";

    private static IFacilityRepository<Facility> repository;

    private FacilityRepository() {
    }
    public static IFacilityRepository<Facility> getInstance(){
        if(repository==null){
            repository=new FacilityRepository();
        }
        return repository;
    }
    @Override
    public Map<Integer, Facility> findAll(String sortByName) {
        Map<Integer, Facility> facilityMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        Facility facility;
        FacilityType facilityType;
        RentType rentType;
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString("facility_type_name");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double area = rs.getDouble("area");
                double cost = rs.getDouble("cost");
                int maxPeople = rs.getInt("max_people");
                int rentTypeId = rs.getInt("rent_type_id");
                String rentTypeName = rs.getString("rent_type_name");
                int facilityTypeId = rs.getInt("facility_type_id");
                String standardRoom = rs.getString("standard_room");
                String description = rs.getString("description_other_convenience");
                double poolArea = rs.getDouble("pool_area");
                int floor = rs.getInt("number_of_floors");
                String facilityFree = rs.getString("facility_free");
                rentType = new RentType(rentTypeId, rentTypeName);
                facilityType = new FacilityType(facilityTypeId, type);
                switch (type.toLowerCase().trim()) {
                    case "villa":
                        facility = new Villa(id, name, area, cost, maxPeople, rentType, facilityType, description, standardRoom, floor, poolArea);
                        break;
                    case "house":
                        facility = new House(id, name, area, cost, maxPeople, rentType, facilityType, description, standardRoom, floor);
                        break;
                    default:
                        facility = new Room(id, name, area, cost, maxPeople, rentType, facilityType, description, facilityFree);
                }
                facilityMap.put(id, facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityMap;
    }

    @Override
    public Map<Integer, Facility> find(String name, String facilityTypeName, String rentTypeName, String sortByName) {
        Map<Integer, Facility> facilityMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        Facility facility;
        FacilityType facilityType;
        RentType rentType;
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_SEARCH);
            pre.setString(1, "%"+(name==null?"":name)+"%");
            pre.setString(2, "%"+(facilityTypeName==null?"":facilityTypeName)+"%");
            pre.setString(3, "%"+(rentTypeName==null?"":rentTypeName)+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String type = rs.getString("facility_type_name");
                int id = rs.getInt("id");
                String facilityName = rs.getString("name");
                double area = rs.getDouble("area");
                double cost = rs.getDouble("cost");
                int maxPeople = rs.getInt("max_people");
                int rentTypeId = rs.getInt("rent_type_id");
                String rent = rs.getString("rent_type_name");
                int facilityTypeId = rs.getInt("facility_type_id");
                String standardRoom = rs.getString("standard_room");
                String description = rs.getString("description_other_convenience");
                double poolArea = rs.getDouble("pool_area");
                int floor = rs.getInt("number_of_floors");
                String facilityFree = rs.getString("facility_free");
                rentType = new RentType(rentTypeId, rent);
                facilityType = new FacilityType(facilityTypeId, type);
                switch (type.toLowerCase().trim()) {
                    case "villa":
                        facility = new Villa(id, facilityName, area, cost, maxPeople, rentType, facilityType, description, standardRoom, floor, poolArea);
                        break;
                    case "house":
                        facility = new House(id, facilityName, area, cost, maxPeople, rentType, facilityType, description, standardRoom, floor);
                        break;
                    default:
                        facility = new Room(id, facilityName, area, cost, maxPeople, rentType, facilityType, description, facilityFree);
                }
                facilityMap.put(id, facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilityMap;
    }

    @Override
    public Facility findByID(int id) {
        
        Connection con = ConnectionDataBase.getConnection();
        Facility facility =null;
        FacilityType facilityType;
        RentType rentType;
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String type = rs.getString("facility_type_name");
                String name = rs.getString("name");
                double area = rs.getDouble("area");
                double cost = rs.getDouble("cost");
                int maxPeople = rs.getInt("max_people");
                int rentTypeId = rs.getInt("rent_type_id");
                String rentTypeName = rs.getString("rent_type_name");
                int facilityTypeId = rs.getInt("facility_type_id");
                String standardRoom = rs.getString("standard_room");
                String description = rs.getString("description_other_convenience");
                double poolArea = rs.getDouble("pool_area");
                int floor = rs.getInt("number_of_floors");
                String facilityFree = rs.getString("facility_free");
                rentType = new RentType(rentTypeId, rentTypeName);
                facilityType = new FacilityType(facilityTypeId, type);
                switch (type.toLowerCase().trim()) {
                    case "villa":
                        facility = new Villa(id, name, area, cost, maxPeople, rentType, facilityType, description, standardRoom, floor, poolArea);
                        break;
                    case "house":
                        facility = new House(id, name, area, cost, maxPeople, rentType, facilityType, description, standardRoom, floor);
                        break;
                    default:
                        facility = new Room(id, name, area, cost, maxPeople, rentType, facilityType, description, facilityFree);
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facility;
    }

    @Override
    public boolean delete(int id) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(DELETE_FACILITY);
            pre.setInt(1, id);
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Facility element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(UPDATE);
            pre.setString(1, element.getNameFacility());
            pre.setDouble(2,element.getLeasedArea());
            pre.setDouble(3,element.getRentalCosts());
            pre.setInt(4,element.getMaxPerson());
            pre.setString(6,element.getDescription());
            if("Villa".equals(element.getFacilityType().getName())){
                pre.setString(5,((Villa)element).getRoom());
                pre.setDouble(7,((Villa)element).getAreaPool());
                pre.setDouble(8,((Villa)element).getFloor());
                pre.setString(9,null);
            }else if("House".equals(element.getFacilityType().getName())){
                pre.setString(5,((House)element).getRoom());
                pre.setString(7,null);
                pre.setDouble(8,((House)element).getFloor());
                pre.setString(9,null);
            }else{
                pre.setString(5,null);
                pre.setString(7,null);
                pre.setString(8,null);
                pre.setString(9,((Room)element).getFree());
            }
            pre.setInt(10,element.getRentalType().getId());
            pre.setInt(11,element.getFacilityType().getId());
            pre.setInt(12,element.getIDFacility());
            return pre.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Facility element) {
       /* `name`, area, cost, max_people, " +
            "standard_room, description_other_convenience, pool_area, number_of_floors, " +
            "facility_free, rent_type_id,facility_type_id */
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(INSERT);
            pre.setString(1, element.getNameFacility());
            pre.setDouble(2,element.getLeasedArea());
            pre.setDouble(3,element.getRentalCosts());
            pre.setInt(4,element.getMaxPerson());
            pre.setString(6,element.getDescription());
            if("Villa".equals(element.getFacilityType().getName())){
                pre.setString(5,((Villa)element).getRoom());
                pre.setDouble(7,((Villa)element).getAreaPool());
                pre.setDouble(8,((Villa)element).getFloor());
                pre.setString(9,null);
            }else if("House".equals(element.getFacilityType().getName())){
                pre.setString(5,((House)element).getRoom());
                pre.setString(7,null);
                pre.setDouble(8,((House)element).getFloor());
                pre.setString(9,null);
            }else{
                pre.setString(5,null);
                pre.setString(7,null);
                pre.setString(8,null);
                pre.setString(9,((Room)element).getFree());
            }
            pre.setInt(10,element.getRentalType().getId());
            pre.setInt(11,element.getFacilityType().getId());
            return pre.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
