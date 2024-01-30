package repositories;

import entities.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    User getUserByUsername(String username);
    List<User> getAllUsers();

}
