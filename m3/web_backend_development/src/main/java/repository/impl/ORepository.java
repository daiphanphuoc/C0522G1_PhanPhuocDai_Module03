package repository.impl;

import model.O;
import repository.IORepository;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ORepository implements IORepository<O> {
    private static final String SQL = "";


    private static IORepository<O> repository;

    private ORepository() {
    }

    public synchronized static IORepository<O> getInstance() {
        if (repository == null) {
            repository = new ORepository();
        }
        return repository;
    }
    @Override
    public Map<Integer, O> findAll() {
        Map<Integer,O> oMap = new HashMap<>();
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            ResultSet resultSet= pre.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return oMap;
    }

    @Override
    public Map<Integer, O> find(String search) {
        Map<Integer,O> oMap = new HashMap<>();
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            ResultSet resultSet= pre.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return oMap;
    }

    @Override
    public Map<Integer, O> findNotID(int id) {
        Map<Integer,O> oMap = new HashMap<>();
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            ResultSet resultSet= pre.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return oMap;
    }

    @Override
    public O findID(int id) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            ResultSet resultSet= pre.executeQuery();
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
    public boolean insert(O element) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            int col= pre.executeUpdate();
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
    public boolean update(O element) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            int col= pre.executeUpdate();
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
    public boolean delete(int id) {
        Connection con = ConnectionUtil.getConnection();
        try {
            PreparedStatement pre =con.prepareStatement(SQL);
            int col= pre.executeUpdate();
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
