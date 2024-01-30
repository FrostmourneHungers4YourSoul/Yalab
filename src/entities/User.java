package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User extends AbstractUser {
    List<MeterReading> meterReadingsList = new ArrayList<>();

    public User(String username, String password) {
        super(username, password);
    }

    public void addMeterReading(MeterReading meterReading){
        meterReadingsList.add(meterReading);
    }

    public void updateMeterReading(MeterReading meterReading){
        meterReadingsList.stream()
                        .filter(meter -> meter.getId() == meterReading.getId())
                        .findFirst()
                        .ifPresentOrElse(
                        meter -> meter.updateFrom(meterReading),
                        () -> System.out.println(meterReading.getId() + " not found in the user's purchase list.")
                );
    }
    public List<MeterReading> getUserMeterReadings(){
        return new LinkedList<>(meterReadingsList);
    }


    /*public LocalDate getDateToNextSet(LocalDate date) {
        return meterReadingsList.stream()
                .filter(meter -> meter.getDateToSetMeterReading().isEqual(date))
                .findFirst()
                .map(MeterReading::getDateToNextSet)
                .orElse(null);
    }*/
    @Override
    public String getRole() {
        return "User";
    }
}
