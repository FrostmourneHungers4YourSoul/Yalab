package controllers;

import entities.MeterReading;
import entities.MeterReadingExtend;
import entities.User;
import services.UserService;

import java.time.LocalDate;
import java.util.List;

public class UserController {
    private final UserService userService;
    public UserService getUserService() {
        return userService;
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public void createUser(String username, String password) {
        User user = new User(username, password);
        userService.getUserRepository().addUser(user);
        System.out.println("User created successfully.");
    }


    public User getUser(String username) {
        return userService.getUserByUsername(username);
    }

    public void getMeterReadingsByUsername(String username){
        List<MeterReading> list = getUser(username).getUserMeterReadings();
        for (MeterReading item: list) {
            System.out.println("=================================================");
            System.out.println(item.getId() + "\n"
                    + item.getDateToSetMeterReading()
                    + "\nCold Water Meter: " + item.getColdWaterMeter()
                    + "\nHot Water Meter: " + item.getHotWaterMeter()
                    + "\nHeating Meter:  " + item.getHeatingMeter());
            System.out.println("=================================================");
        }
    }

    public void CurrentReadingsByUsername(String username){
        int size = getUser(username).getUserMeterReadings().size();
        MeterReading element =  getUser(username).getUserMeterReadings().get(size - 1);
        System.out.println("Date: " + element.getDateToSetMeterReading());
        System.out.println("Cold Water Meter: " + element.getColdWaterMeter());
        System.out.println("Hot Water Meter: " + element.getHotWaterMeter());
        System.out.println("Heating Meter: " + element.getHeatingMeter());
    }
    public void addMeterReading(String username, int id, LocalDate dateToGetMeterReading,
                                double coldWaterMeter, double hotWaterMeter, double heatingMeter) {
        if (getUser(username).getUserMeterReadings().isEmpty()){
            MeterReadingExtend meterReading = new MeterReadingExtend(id, dateToGetMeterReading,
                    coldWaterMeter, hotWaterMeter, heatingMeter);
            userService.createMeterReading(meterReading, username);
            System.out.println("Meter Reading added successfully.");
        }
        else{
            int size = getUser(username).getUserMeterReadings().size();
            MeterReading element = getUser(username).getUserMeterReadings().get(size - 1);
            LocalDate localDate = element.getDateToNextSet();

            if (dateToGetMeterReading.isAfter(localDate)) {
                MeterReadingExtend meterReading = new MeterReadingExtend(id, dateToGetMeterReading,
                        coldWaterMeter, hotWaterMeter, heatingMeter);
                userService.createMeterReading(meterReading, username);
                System.out.println("Meter Reading added successfully.");
            }
            else System.out.println("Readings can only be submitted once per month." +
                    "\nNext submit " + localDate);
        }

    }

    public void getAllUsers() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println("User: " + user.getUsername());
        }
    }

    public boolean isUserReged(String username){
        return getUser(username) != null;
    }
}