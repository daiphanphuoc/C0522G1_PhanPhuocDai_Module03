package repository.impl.business;

import Util.ConnectionDataBase;
import model.business.ContractDetail;
import org.jetbrains.annotations.NotNull;
import repository.business.IContractDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ContractDetailRepository implements IContractDetailRepository<ContractDetail> {
    private static final String SELECT_BY_ID = "select * from contract_detail where is_delete =0 and id = ?;";
    private static final String SELECT_ALL = "select * from contract_detail where is_delete =0;";
    private static final String DELETE = "update contract_detail set is_delete = 1  " +
            "where is_delete = 0 and id = ?;";
    private static final String UPDATE = "update contract_detail set contract_id = ?, attach_facility_id = ?, quantity = ?  " +
            "where is_delete = 0 and id = ?;";
    private static final String INSERT = "insert into contract_detail(contract_id,attach_facility_id,quantity) " +
            "values (?,?,?);";

    @Override
    public Map<Integer, ContractDetail> findAll() {
        Map<Integer, ContractDetail> contractMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            ContractDetail contract;
            while (rs.next()) {
                int id = rs.getInt("id");
                int contractId = rs.getInt("contract_id");
                int facilityId = rs.getInt("attach_facility_id");
                int quantity = rs.getInt("quantity");
                contract = new ContractDetail(id, contractId, facilityId, quantity);
                contractMap.put(id, contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractMap;
    }

    @Override
    public ContractDetail findByID(int id) {
        ContractDetail contract = null;
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_BY_ID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int contractId = rs.getInt("contract_id");
                int facilityId = rs.getInt("attach_facility_id");
                int quantity = rs.getInt("quantity");
                contract = new ContractDetail(id, contractId, facilityId, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
        return false;
    }

    @Override
    public boolean update(@NotNull ContractDetail element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(UPDATE);
            pre.setInt(1, element.getContractId());
            pre.setInt(2, element.getAttachFacilityId());
            pre.setInt(3, element.getQuantity());
            pre.setInt(4, element.getId());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(@NotNull ContractDetail element) {
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(INSERT);
            pre.setInt(1, element.getContractId());
            pre.setInt(2, element.getAttachFacilityId());
            pre.setInt(3, element.getQuantity());
            return pre.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
