package service.impl;

import model.User;
import repository.IUserRepository;
import repository.impl.UserRepository;
import service.IUserService;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {
    IUserRepository repository = new UserRepository();

    @Override
    public void insertUser(User user) throws SQLException {
        repository.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return repository.selectUser(id);
    }

    @Override
    public User selectUserSP(int id) {
        return repository.selectUserSP(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return repository.selectAllUsers();
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return repository.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return repository.updateUser(user);
    }

    @Override
    public List<User> searchByCountry(String country) {
        return repository.searchByCountry(country);
    }

    @Override
    public List<User> sortByName(String sort) {
        return repository.sortByName(sort);
    }

    @Override
    public void insertUserStore(User user) {
        repository.insertUserStore(user);
    }

    @Override
    public void addUserTransaction(User user, int[] permisions) {
        repository.addUserTransaction(user,permisions);
    }

    @Override
    public void insertUpdateWithoutTransaction() {
        repository.insertUpdateWithoutTransaction();
    }

    @Override
    public void insertUpdateUseTransaction() {
        repository.insertUpdateUseTransaction();
    }
}
