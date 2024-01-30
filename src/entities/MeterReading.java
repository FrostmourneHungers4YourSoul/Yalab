package entities;

import java.time.LocalDate;

public abstract class MeterReading {
    private int id;
    private LocalDate dateToSetMeterReading;
    private LocalDate dateToNextSet;

    private double coldWaterMeter;
    private double hotWaterMeter;
    private double heatingMeter;

    public MeterReading(int id, LocalDate dateToGetMeterReading, double coldWaterMeter,
                        double hotWaterMeter, double heatingMeter)
    {
        this.id = id;
        this.dateToSetMeterReading = dateToGetMeterReading;
        this.coldWaterMeter = coldWaterMeter;
        this.hotWaterMeter = hotWaterMeter;
        this.heatingMeter = heatingMeter;
    }

    public void updateFrom(MeterReading other) {
        this.setId(other.getId());
    }
    public LocalDate getDateToSetMeterReading() {
        return dateToSetMeterReading;
    }

    public void setDateToSetMeterReading(LocalDate dateToSetMeterReading) {
        this.dateToSetMeterReading = dateToSetMeterReading;
    }

    public LocalDate getDateToNextSet() {
        return dateToNextSet;
    }

    private void setDateToNextSet() {
        LocalDate date = getDateToSetMeterReading();
        this.dateToNextSet = date.plusMonths(1);
    }

    public double getColdWaterMeter() {
        return coldWaterMeter;
    }

    public void setColdWaterMeter(double coldWaterMeter) {
        this.coldWaterMeter = coldWaterMeter;
    }

    public double getHotWaterMeter() {
        return hotWaterMeter;
    }

    public void setHotWaterMeter(double hotWaterMeter) {
        this.hotWaterMeter = hotWaterMeter;
    }

    public double getHeatingMeter() {
        return heatingMeter;
    }

    public void setHeatingMeter(double heatingMeter) {
        this.heatingMeter = heatingMeter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
