package services;

import entities.Admin;
import entities.MeterReading;
import entities.User;
import repositories.AdminRepository;
import repositories.UserRepository;

import java.util.Collections;
import java.util.List;

public class AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Admin getAdminByName(String name) {
        return adminRepository.getAdminByName(name);
    }

    public boolean isAdminsListEmpty(){
        return adminRepository.getAllAdmins().isEmpty();
    }
    public List<Admin> getAllAdmins() {
        return adminRepository.getAllAdmins();
    }
    public AdminRepository getAdminRepository() {
        return adminRepository;
    }

    public List<MeterReading> getAllMeterReadingsForUser(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            return user.getUserMeterReadings();
        } else {
            return Collections.emptyList();
        }
    }

    public List<User> getAllMeterReadingsForAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
