package repository;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    void insertUser(User user) throws SQLException;

    User selectUser(int id);

    User selectUserSP(int id);

    List<User> selectAllUsers();

    boolean deleteUser(int id) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    List<User> searchByCountry(String country);

    List<User> sortByName(String sort);

    void insertUserStore(User user);

    void addUserTransaction(User user, int[] permisions);

    public void insertUpdateWithoutTransaction();

    public void insertUpdateUseTransaction();

}
