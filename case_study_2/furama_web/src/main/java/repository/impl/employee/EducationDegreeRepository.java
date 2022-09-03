package repository.impl.employee;

import Util.ConnectionDataBase;
import model.person.EducationDegree;
import model.person.Position;
import repository.IRepository;
import repository.employee.IEducationDegreeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EducationDegreeRepository implements IEducationDegreeRepository<EducationDegree> {

    private static final String SELECT_ALL = "select * from education_degree where is_delete = 0 ";
    private static final String SELECT_BY_ID =  "select * from education_degree where is_delete = 0 and id = ?;";

    private static IRepository<EducationDegree> repository;

    private EducationDegreeRepository() {
    }

    public static synchronized IRepository<EducationDegree> getInstance() {
        if (repository == null) {
            repository = new EducationDegreeRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, EducationDegree> findAll() {
        Map<Integer, EducationDegree> degreeMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            EducationDegree degree;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                degree = new EducationDegree(id, name);
                degreeMap.put(id, degree);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degreeMap;
    }

    @Override
    public EducationDegree findByID(int id) {
        Connection con = ConnectionDataBase.getConnection();

        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new EducationDegree(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
