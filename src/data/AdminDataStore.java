package data;

import entities.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDataStore implements DataStore<Admin>{
    private List<Admin> admins = new ArrayList<>();

    @Override
    public void addEntity(Admin admin) {
        admins.add(admin);
    }

    @Override
    public List<Admin> getAllEntities() {
        return new ArrayList<>(admins);
    }
}
