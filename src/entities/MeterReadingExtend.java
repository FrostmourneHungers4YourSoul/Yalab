package entities;

import java.time.LocalDate;

public class MeterReadingExtend extends MeterReading{


    public MeterReadingExtend(int id, LocalDate dateToGetMeterReading, double coldWaterMeter,
                              double hotWaterMeter, double heatingMeter)
    {
        super(id,dateToGetMeterReading, coldWaterMeter, hotWaterMeter, heatingMeter);
    }


}
