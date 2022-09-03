package repository.impl.employee;

import Util.ConnectionDataBase;
import model.person.CustomerType;
import model.person.Division;
import model.person.Position;
import repository.IRepository;
import repository.employee.IPositionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PositionRepository implements IPositionRepository<Position> {
    private static final String SELECT_BY_ID ="select * from `position` where is_delete =0 and id = ?;";
    private static final String SELECT_ALL = "select * from `position` where is_delete =0;";

    private static IRepository<Position> repository;

    private PositionRepository() {
    }

    public static synchronized IRepository<Position> getInstance() {
        if (repository == null) {
            repository = new PositionRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, Position> findAll() {
        Map<Integer, Position> positionMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            Position position;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                position = new Position(id, name);
                positionMap.put(id, position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionMap;
    }

    @Override
    public Position findByID(int id) {
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new Position(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

