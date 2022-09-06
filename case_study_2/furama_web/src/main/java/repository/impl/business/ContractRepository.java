package repository.impl.business;

import Util.ConnectionDataBase;
import model.business.Contract;
import repository.business.IContractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContractRepository implements IContractRepository<Contract> {
    private static final String SELECT_BY_ID = "select * from contract where is_delete =0 and id = ?;";
    private static final String SELECT_ALL = "select * from contract where is_delete =0;";
    private static final String UPDATE = "update contract set start_date = ?, end_date = ?, deposit = ?," +
            " employee_id = ?, customer_id = ?, facility_id= ?,  where is_delete =0 and id = ?;";
    private static final String DELETE = "update contract set is_delete = 1  " +
            "where is_delete = 0 and id = ?;";
    private static final String INSERT = "insert into contract(start_date,end_date,deposit,employee_id,customer_id,facility_id) " +
            "values(?,?,?,?,?,?); ";

    private static IContractRepository<Contract> repository;

    private ContractRepository() {
    }

    public synchronized static IContractRepository<Contract> getInstance() {
        if (repository == null) {
            repository = new ContractRepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, Contract> findAll() {
        Map<Integer, Contract> contractMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            Contract contract;
            while (rs.next()) {
                /*start_date,end_date,deposit,employee_id,customer_id,facility_id*/
                int id = rs.getInt("id");

                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");
                double deposit = rs.getDouble("deposit");
                int employee = rs.getInt("employee_id");
                int customer = rs.getInt("customer_id");
                int facility = rs.getInt("facility_id");
                contract = new Contract(id, start, end, deposit, customer, employee, facility);
                contractMap.put(id, contract);
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
        return contractMap;
    }

    @Override
    public Contract findByID(int id) {
        Contract contract = null;
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                /*start_date,end_date,deposit,employee_id,customer_id,facility_id*/
                Date start = rs.getDate("start_date");
                Date end = rs.getDate("end_date");
                double deposit = rs.getDouble("deposit");
                int employee = rs.getInt("employee_id");
                int customer = rs.getInt("customer_id");
                int facility = rs.getInt("facility_id");
                contract = new Contract(id, start, end, deposit,customer, employee,  facility);

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
        return contract;
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
    public boolean update(Contract element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(UPDATE);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(element.getStart());
            pre.setString(1, start);
            String end = df.format(element.getEnd());
            pre.setString(2, end);
            pre.setDouble(3, element.getDeposit());
            pre.setInt(4, element.getiDEmployee());
            pre.setInt(5, element.getiDCustomer());
            pre.setInt(6, element.getiDFacility());
            pre.setInt(7, element.getContractID());
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
    public boolean insert(Contract element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(INSERT);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String start = df.format(element.getStart());
            pre.setString(1, start);
            String end = df.format(element.getEnd());
            pre.setString(2, end);
            pre.setDouble(3, element.getDeposit());
            pre.setInt(4, element.getiDEmployee());
            pre.setInt(5, element.getiDCustomer());
            pre.setInt(6, element.getiDFacility());
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
