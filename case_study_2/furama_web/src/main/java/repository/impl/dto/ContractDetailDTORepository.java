package repository.impl.dto;

import Util.ConnectionDataBase;
import model.business.Contract;
import model.business.ContractDetail;
import model.dto.ContractDetailDTO;
import repository.business.IContractDetailRepository;
import repository.dto.IContractDetailDTORepository;
import repository.impl.business.ContractDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ContractDetailDTORepository implements IContractDetailDTORepository<ContractDetailDTO> {
    private static final String SELECT_ALL = "select attach_facility.*," +
            " contract_detail.contract_id as contract_id, contract_detail.id as detail_id," +
            " contract_detail.quantity as quantity " +
            "from attach_facility inner join contract_detail" +
            " on contract_detail.attach_facility_id=attach_facility.id " +
            "where contract_detail.is_delete =0 and attach_facility.is_delete =0;";
    private static final String SELECT_ID_CONTRACT ="select attach_facility.*," +
            " contract_detail.contract_id as contract_id, contract_detail.id as detail_id," +
            " contract_detail.quantity as quantity " +
            "from attach_facility inner join contract_detail" +
            " on contract_detail.attach_facility_id=attach_facility.id " +
            "where contract_detail.is_delete =0 and attach_facility.is_delete =0 and contract_id = ?;";

    private static IContractDetailDTORepository<ContractDetailDTO> repository;

    private ContractDetailDTORepository() {
    }

    public synchronized static IContractDetailDTORepository<ContractDetailDTO> getInstance() {
        if (repository == null) {
            repository = new ContractDetailDTORepository();
        }
        return repository;
    }

    @Override
    public Map<Integer, ContractDetailDTO> findAll() {
        Map<Integer, ContractDetailDTO> contractMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ALL);
            ResultSet rs = pre.executeQuery();
            ContractDetailDTO contract;
            while (rs.next()) {
                /*start_date,end_date,deposit,employee_id,customer_id,facility_id*/
                int idAttach = rs.getInt("id");
                String name = rs.getString("name");
                double cost = rs.getDouble("cost");
                String unit = rs.getString("unit");
                String status = rs.getString("status");

                int idContract = rs.getInt("contract_id");
                int idDetail = rs.getInt("detail_id");
                int quantity = rs.getInt("quantity");
                contract = new ContractDetailDTO(idContract,idDetail,idAttach,name,cost,quantity,unit,status);
                contractMap.put(idDetail, contract);
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
    public ContractDetailDTO findByID(int id) {
        return null;
    }

    @Override
    public Map<Integer, ContractDetailDTO> find(int idContract) {
        Map<Integer, ContractDetailDTO> contractMap = new HashMap<>();
        Connection con = ConnectionDataBase.getConnection();
        try {
            PreparedStatement pre = con.prepareStatement(SELECT_ID_CONTRACT);
            pre.setInt(1,idContract);
            ResultSet rs = pre.executeQuery();
            ContractDetailDTO contract;
            while (rs.next()) {
                int idAttach = rs.getInt("id");
                String name = rs.getString("name");
                double cost = rs.getDouble("cost");
                String unit = rs.getString("unit");
                String status = rs.getString("status");
                int idDetail = rs.getInt("detail_id");
                int quantity = rs.getInt("quantity");
                contract = new ContractDetailDTO(idContract,idDetail,idAttach,name,cost,quantity,unit,status);
                contractMap.put(idDetail, contract);
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
}
