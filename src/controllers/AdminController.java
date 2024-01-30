package controllers;

import entities.Admin;
import entities.MeterReading;
import entities.User;
import services.AdminService;

import java.util.List;

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void createAdmin(String username, String password) {
        Admin admin = new Admin(username, password);
        adminService.getAdminRepository().addAdmin(admin);
        System.out.println("Admin created successfully.");
    }

    public Admin getAdminByName(String name){
        return adminService.getAdminByName(name);
    }

    public void getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        System.out.println("List of admins:");
        for (Admin admin : admins) {
            System.out.println("Admin: " + admin.getUsername());
        }
    }

    public void getAllMeterReadingsForUser(String username) {
        List<MeterReading> meterReadings = adminService.getAllMeterReadingsForUser(username);
        System.out.println("Meter readings for user " + username + ":");
        for (MeterReading meterReading : meterReadings) {
            System.out.println("Date: " + meterReading.getDateToSetMeterReading()
            + "\nCold Water: " + meterReading.getColdWaterMeter()
                    + "\nHot Water: " + meterReading.getHotWaterMeter()
            + "\nHeating " + meterReading.getHeatingMeter());
        }
    }

    public void getAllMeterReadingsForAllUsers() {
        List<User> users = adminService.getAllMeterReadingsForAllUsers();
        System.out.println("Meter readings for all users:");
        for (User user: users) {
            for(MeterReading meterReading: user.getUserMeterReadings()){
                System.out.println("| " + meterReading.getDateToSetMeterReading()
                        + " | " + meterReading.getColdWaterMeter()
                        + " | " + meterReading.getHotWaterMeter()
                        + " | " + meterReading.getHeatingMeter() + " |");
            }
        }
    }
}
