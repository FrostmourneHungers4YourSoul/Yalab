package repositories.impl;

import entities.User;
import repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();
    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }



}
