package repositories.impl;

import entities.Admin;
import entities.User;
import repositories.AdminRepository;

import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    private final List<Admin> admins = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    @Override
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    @Override
    public Admin getAdminByName(String name) {
        return admins.stream()
                .filter(admin -> admin.getUsername().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return new ArrayList<>(admins);
    }
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
