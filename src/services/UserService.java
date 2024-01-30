package services;

import entities.MeterReading;
import entities.User;
import repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


    public User getMeterReadingByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
    public void createMeterReading(MeterReading meterReading, String username){
        userRepository.getUserByUsername(username).addMeterReading(meterReading);
    }
    public void updateMeterReading(MeterReading meterReading,String username){
        userRepository.getUserByUsername(username).updateMeterReading(meterReading);
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }

}
