package data;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataStore implements DataStore<User>{
    private List<User> users = new ArrayList<>();
    @Override
    public void addEntity(User entity) {
        users.add(entity);
    }

    @Override
    public List<User> getAllEntities() {
        return new ArrayList<>(users);
    }
}
