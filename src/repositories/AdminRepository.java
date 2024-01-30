package repositories;

import entities.Admin;

import java.util.List;

public interface AdminRepository {
    void addAdmin(Admin admin);
    Admin getAdminByName(String name);
    List<Admin> getAllAdmins();
}
